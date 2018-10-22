package pl.shoeshop.shoeshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.shoeshop.shoeshop.dto.RateDTO;
import pl.shoeshop.shoeshop.dto.RateSummaryDTO;
import pl.shoeshop.shoeshop.dto.VoteDTO;
import pl.shoeshop.shoeshop.entity.Rate;
import pl.shoeshop.shoeshop.entity.Shoe;
import pl.shoeshop.shoeshop.entity.Vote;
import pl.shoeshop.shoeshop.repository.*;
import pl.shoeshop.shoeshop.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

    private ShoeRepository shoeRepository;

    private RateRepository rateRepository;

    private ReceiverRepository receiverRepository;

    private VoteRepository voteRepository;

    @Autowired
    public RatingServiceImpl(ShoeRepository shoeRepository,
                             RateRepository rateRepository,
                             ReceiverRepository receiverRepository,
                             VoteRepository voteRepository) {
        this.shoeRepository = shoeRepository;
        this.rateRepository = rateRepository;
        this.receiverRepository = receiverRepository;
        this.voteRepository = voteRepository;
    }

    @Override
    public void rate(RateDTO rateDTO) {
        if (couldRate(rateDTO)) {
            shoeRepository.findById(rateDTO.getShoeId())
                    .map(shoe -> createRate(rateDTO, shoe))
                    .ifPresent(rateRepository::save);
        }
    }

    @Override
    public void vote(VoteDTO voteDTO) {
        if (couldVote(voteDTO)) {
            rateRepository.findById(voteDTO.getRateId())
                    .filter(rate -> rate.getComment() != null)
                    .map(rate -> createVote(voteDTO, rate))
                    .ifPresent(voteRepository::save);
        }
    }

    @Override
    public RateSummaryDTO getRating(Long shoeId) {
        RateSummaryDTO rateSummaryDTO = new RateSummaryDTO();

        rateSummaryDTO.setSummary(rateRepository.getRatingSummary(shoeId));
        rateSummaryDTO.setComments(rateRepository.getComments(shoeId));

        return rateSummaryDTO;
    }

    private boolean couldRate(RateDTO rateDTO) {
        return rateRepository.couldRate(rateDTO.getIdentityEmail(), rateDTO.getOrderId(), rateDTO.getShoeId());
    }

    private Boolean couldVote(VoteDTO vote) {
        return rateRepository.couldVote(vote.getIdentityEmail(), vote.getRateId());
    }

    private Rate createRate(RateDTO rateDTO, Shoe shoe) {
        Rate rate = new Rate();

        rate.setRate(rateDTO.getRate());
        rate.setComment(rateDTO.getComment());
        rate.setShoe(shoe);

        return rate;
    }

    private Vote createVote(VoteDTO voteDTO, Rate rate) {
        Vote vote = new Vote();
        vote.setIsUseful(voteDTO.getIsUseful());
        vote.setReceiver(receiverRepository.findByEmail(voteDTO.getIdentityEmail()));
        vote.setRate(rate);

        return vote;
    }
}
