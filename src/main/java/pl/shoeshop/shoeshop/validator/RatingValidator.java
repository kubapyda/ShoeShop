package pl.shoeshop.shoeshop.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.shoeshop.shoeshop.dto.RateDTO;
import pl.shoeshop.shoeshop.dto.VoteDTO;
import pl.shoeshop.shoeshop.repository.RateRepository;

import javax.annotation.Nonnull;

import static pl.shoeshop.shoeshop.validator.ErrorConstants.ALREADY_RATED_OR_HAVE_NOT_BOUGHT;
import static pl.shoeshop.shoeshop.validator.ErrorConstants.ALREADY_VOTED_OR_NOT_A_CUSTOMER;

@Component
public class RatingValidator implements Validator {

    private RateRepository rateRepository;

    @Autowired
    public RatingValidator(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public boolean supports(@Nonnull Class<?> aClass) {
        return RateDTO.class.equals(aClass) || VoteDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, @Nonnull Errors errors) {
        if (o instanceof RateDTO) {
            validate((RateDTO) o, errors);
        } else if (o instanceof VoteDTO) {
            validate((VoteDTO) o, errors);
        }
    }

    private void validate(RateDTO rateDTO, @Nonnull Errors errors) {
        if (!rateRepository.couldRate(rateDTO.getIdentityEmail(), rateDTO.getOrderId(), rateDTO.getShoeId())) {
            errors.reject(ALREADY_RATED_OR_HAVE_NOT_BOUGHT);
        }
    }

    private void validate(VoteDTO voteDTO, @Nonnull Errors errors) {
        if (!rateRepository.couldVote(voteDTO.getIdentityEmail(), voteDTO.getRateId())) {
            errors.reject(ALREADY_VOTED_OR_NOT_A_CUSTOMER);
        }
    }
}
