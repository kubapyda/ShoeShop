package pl.shoeshop.shoeshop.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.tools.ToolContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import pl.shoeshop.shoeshop.dto.OrderedShoeDTO;
import pl.shoeshop.shoeshop.dto.RatingLinkDTO;
import pl.shoeshop.shoeshop.entity.Order;
import pl.shoeshop.shoeshop.entity.Receiver;
import pl.shoeshop.shoeshop.projection.OrderedShoeProjection;
import pl.shoeshop.shoeshop.repository.OrderedShoeRepository;
import pl.shoeshop.shoeshop.security.FrontendProperties;
import pl.shoeshop.shoeshop.service.MailService;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MailServiceImpl implements MailService {

    private JavaMailSender mailSender;

    private OrderedShoeRepository orderedShoeRepository;

    private FrontendProperties frontendProperties;

    private VelocityEngine velocityEngine;

    @Autowired
    private MailServiceImpl(JavaMailSender mailSender,
                            OrderedShoeRepository orderedShoeRepository,
                            FrontendProperties frontendProperties) {
        this.mailSender = mailSender;
        this.orderedShoeRepository = orderedShoeRepository;
        this.frontendProperties = frontendProperties;
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
        List<OrderedShoeProjection> orderedShoeProjections = orderedShoeRepository.getOrderedShoes(order.getId());

        Context context = new ToolContext();
        context.put("receiver", order.getReceiver().getName());
        context.put("orderedShoes", toDTO(orderedShoeProjections));
        context.put("totalPrice", calculateTotalPrice(orderedShoeProjections));
        context.put("ratingLinks", createRatingLinks(order, orderedShoeProjections));

        return context;
    }

    private double calculateTotalPrice(List<OrderedShoeProjection> orderedShoes) {
        return orderedShoes.stream()
                .mapToDouble(dto -> dto.getPrice().doubleValue() * dto.getQuantity())
                .sum();
    }

    private List<OrderedShoeDTO> toDTO(List<OrderedShoeProjection> orderedShoeProjections) {
        return orderedShoeProjections.stream()
                .map(OrderedShoeDTO::new)
                .collect(Collectors.toList());
    }

    private List<RatingLinkDTO> createRatingLinks(Order order, List<OrderedShoeProjection> orderedShoes) {
        return orderedShoes.stream()
                .map(shoe -> toRatingLink(order, shoe))
                .distinct()
                .collect(Collectors.toList());
    }

    private RatingLinkDTO toRatingLink(Order order, OrderedShoeProjection shoe) {
        RatingLinkDTO ratingLinkDTO = new RatingLinkDTO();

        ratingLinkDTO.setRatingLink(getUri(order, shoe).toString());
        ratingLinkDTO.setShoeName(shoe.getBrand() + " " + shoe.getModel());

        return ratingLinkDTO;
    }

    private URI getUri(Order order, OrderedShoeProjection shoe) {
        Long orderId = order.getId();
        String email = order.getReceiver().getEmail();

        String encodedEmail;
        try {
            encodedEmail = URLEncoder.encode(email, StandardCharsets.US_ASCII.toString());
        } catch (UnsupportedEncodingException e) {
            encodedEmail = StringUtils.EMPTY;
            e.printStackTrace();
        }

        return UriComponentsBuilder.fromPath(frontendProperties.getUrl())
                .queryParam("email", encodedEmail)
                .path("/rate-order/{id}/shoe/{shoeId}")
                .buildAndExpand(orderId, shoe.getShoeId())
                .toUri();
    }
}
