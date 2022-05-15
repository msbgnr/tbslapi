package com.biggergames.tbslapi.validation.validator;

import com.biggergames.tbslapi.client.request.match.MatchScoreRequest;
import com.biggergames.tbslapi.validation.constraints.MatchScore;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchScoreValidator implements ConstraintValidator<MatchScore, Object> {
    private String message;

    @Override
    public void initialize(MatchScore constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean valid = false;
        if (value instanceof MatchScoreRequest) {
            MatchScoreRequest request = (MatchScoreRequest) value;
            valid = isCorrect(request);
        }

        if (!valid) {
            context.buildConstraintViolationWithTemplate(message).addPropertyNode("home").addConstraintViolation().disableDefaultConstraintViolation();
        }

        return valid;
    }

    private boolean isCorrect(MatchScoreRequest request) {
        return isRejectCorrectHome(request.getHome()) && isRejectCorrectGuest(request.getGuest()) && isRejectCorrectHScore(request.getHScore())
                && isRejectCorrectGScore(request.getGScore());
    }

    private boolean isRejectCorrectHome(String homeTeam) {
        if (StringUtils.isEmpty(homeTeam)) {
            message = "home value cannot be empty";
            return false;
        }
        return true;
    }

    private boolean isRejectCorrectGuest(String guestTeam) {
        if (StringUtils.isEmpty(guestTeam)) {
            message = "guest value cannot be empty";
            return false;
        }
        return true;
    }

    private boolean isRejectCorrectHScore(int hScore) {
        if (hScore < 0) {
            message = "hScore value must be bigger than 0";
            return false;
        }
        return true;
    }

    private boolean isRejectCorrectGScore(int gScore) {
        if (gScore < 0) {
            message = "gScore value must be bigger than 0";
            return false;
        }
        return true;
    }
}
