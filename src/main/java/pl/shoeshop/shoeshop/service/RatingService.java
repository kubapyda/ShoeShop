package pl.shoeshop.shoeshop.service;

import pl.shoeshop.shoeshop.dto.RateDTO;
import pl.shoeshop.shoeshop.dto.RateSummaryDTO;
import pl.shoeshop.shoeshop.dto.VoteDTO;

public interface RatingService {

    void vote(VoteDTO vote);

    void rate(RateDTO rate);

    RateSummaryDTO getRating(Long shoeId);
}
