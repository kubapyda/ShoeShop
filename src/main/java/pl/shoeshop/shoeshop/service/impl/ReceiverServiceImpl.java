package pl.shoeshop.shoeshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.shoeshop.shoeshop.dto.ReceiverDTO;
import pl.shoeshop.shoeshop.entity.Receiver;
import pl.shoeshop.shoeshop.repository.ReceiverRepository;
import pl.shoeshop.shoeshop.service.ReceiverService;

@Service
public class ReceiverServiceImpl implements ReceiverService {

    private ReceiverRepository receiverRepository;

    @Autowired
    public ReceiverServiceImpl(ReceiverRepository receiverRepository) {

        this.receiverRepository = receiverRepository;
    }

    @Override
    public Receiver getOrCreate(ReceiverDTO receiverDTO) {

        Receiver receiver = receiverRepository.findByEmail(receiverDTO.getEmail());

        if (receiver == null) {
            receiver = Receiver.builder()
                    .name(receiverDTO.getName())
                    .surname(receiverDTO.getSurname())
                    .address(receiverDTO.getAddress())
                    .email(receiverDTO.getEmail())
                    .build();

            receiver = receiverRepository.save(receiver);
        }

        return receiver;
    }
}