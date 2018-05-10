package pl.shoeshop.shoeshop.service.impl;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.tools.ToolContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import pl.shoeshop.shoeshop.dto.OrderedShoeDTO;
import pl.shoeshop.shoeshop.entity.Order;
import pl.shoeshop.shoeshop.entity.Receiver;
import pl.shoeshop.shoeshop.repository.OrderedShoeRepository;
import pl.shoeshop.shoeshop.service.MailService;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MailServiceImpl implements MailService {

    private JavaMailSender mailSender;

    private OrderedShoeRepository orderedShoeRepository;

    private VelocityEngine velocityEngine;

    @Autowired
    private MailServiceImpl(JavaMailSender mailSender, OrderedShoeRepository orderedShoeRepository) {
        this.mailSender = mailSender;
        this.orderedShoeRepository = orderedShoeRepository;
    }

    @PostConstruct
    void init() {
        velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();
    }

    @Override
    public void sendConfirmation(Order order) throws MessagingException {
        Receiver receiver = order.getReceiver();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(receiver.getEmail());
        helper.setSubject(String.format("Order number %08d", order.getId()));

        StringWriter stringWriter = new StringWriter();
        Context context = getContext(order);
        velocityEngine.mergeTemplate("velocity/sendOrderConfirmation_PL.vm", "utf-8", context, stringWriter);
        helper.setText(stringWriter.toString(), true);

        mailSender.send(message);
    }

    private Context getContext(Order order) {
        List<OrderedShoeDTO> orderedShoes = orderedShoeRepository.getOrderedShoes(order.getId())
                .map(OrderedShoeDTO::new)
                .collect(Collectors.toList());

        double totalPrice = orderedShoes.stream()
                .mapToDouble(dto -> dto.getPrice().doubleValue() * dto.getQuantity())
                .sum();

        Context context = new ToolContext();
        context.put("receiver", order.getReceiver().getName());
        context.put("orderedShoes", orderedShoes);
        context.put("totalPrice", totalPrice);
        return context;
    }
}
