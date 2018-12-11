export class ShoeRate {
  summary = new RateSummary();
  comments = new RateComment();
}

class RateSummary {
  rate: number;
  totalRates: number;
}

class RateComment {
  rate: number;
  rateComment: string;
  usability: number;
}
