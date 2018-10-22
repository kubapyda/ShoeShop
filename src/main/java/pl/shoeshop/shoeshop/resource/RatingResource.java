package pl.shoeshop.shoeshop.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.shoeshop.shoeshop.dto.RateDTO;
import pl.shoeshop.shoeshop.dto.RateSummaryDTO;
import pl.shoeshop.shoeshop.dto.VoteDTO;
import pl.shoeshop.shoeshop.service.RatingService;
import pl.shoeshop.shoeshop.validator.RatingValidator;

import javax.validation.Valid;

@RestController
@RequestMapping("rates/")
public class RatingResource {

    private RatingService ratingService;

    private RatingValidator ratingValidator;

    @Autowired
    public RatingResource(RatingService ratingService, RatingValidator ratingValidator) {
        this.ratingService = ratingService;
        this.ratingValidator = ratingValidator;
    }

    @InitBinder
    @SuppressWarnings("unused")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(ratingValidator);
    }

    @RequestMapping(value = "rate", method = RequestMethod.POST)
    public ResponseEntity<?> rate(@Valid @RequestBody RateDTO rate) {
        ratingService.rate(rate);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "vote", method = RequestMethod.POST)
    public ResponseEntity<?> vote(@Valid @RequestBody VoteDTO vote) {
        ratingService.vote(vote);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "shoe/{shoeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RateSummaryDTO> getRates(@PathVariable Long shoeId) {
        RateSummaryDTO rateSummary = ratingService.getRating(shoeId);

        return ResponseEntity.ok(rateSummary);
    }
}