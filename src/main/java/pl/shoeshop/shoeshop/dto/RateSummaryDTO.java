package pl.shoeshop.shoeshop.dto;

import lombok.Data;
import pl.shoeshop.shoeshop.projection.CommentProjection;
import pl.shoeshop.shoeshop.projection.RatingSummaryProjection;

import java.util.List;

@Data
public class RateSummaryDTO {

    private RatingSummaryProjection summary;
    private List<CommentProjection> comments;
}
