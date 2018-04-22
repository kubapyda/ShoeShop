package pl.shoeshop.shoeshop.service;

import pl.shoeshop.shoeshop.dto.ReceiverDTO;
import pl.shoeshop.shoeshop.entity.Receiver;

public interface ReceiverService {

    Receiver getOrCreate(ReceiverDTO receiverDTO);
}
