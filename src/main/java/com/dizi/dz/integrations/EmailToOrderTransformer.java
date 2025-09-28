package com.dizi.dz.integrations;

import com.dizi.dz.entity.Dizi;
import com.dizi.dz.entity.Ingredient;
import com.dizi.dz.entity.IngredientRef;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.mail.transformer.AbstractMailMessageTransformer;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class EmailToOrderTransformer extends AbstractMailMessageTransformer<EmailOrder> {

    private static Logger log = LoggerFactory.getLogger(EmailToOrderTransformer.class);

    private static final String SUBJECT_KEYWORDS = "TACO ORDER";

    @Override
    protected AbstractIntegrationMessageBuilder<EmailOrder> doTransform(Message mailMessage) {
        EmailOrder diziOrder = processPayload(mailMessage);
        return MessageBuilder.withPayload(diziOrder);
    }

    private EmailOrder processPayload(Message mailMessage) {
        try {
            String subject = mailMessage.getSubject();
            if (subject.toUpperCase().contains(SUBJECT_KEYWORDS)) {
                String email = ((InternetAddress) mailMessage.getFrom()[0]).getAddress();
                String content = mailMessage.getContent().toString();
                return parseEmailToOrder(email, content);
            }
        } catch (MessagingException e) {
            log.error("MessagingException: %s", e);
        } catch (IOException e) {
            log.error("IOException: %s", e);
        }
        return null;
    }

    private EmailOrder parseEmailToOrder(String email, String content) {
        EmailOrder order = new EmailOrder(email);
        String[] lines = content.split("\\r?\\n");
        for (String line : lines) {
            if (line.contains(":")) {
                String[] lineSplit = line.split(":");
                String diziName = lineSplit[0].trim();
                String ingredients = lineSplit[1].trim();
                String[] ingredientsSplit = ingredients.split(",");
                List<IngredientRef> ingredientCodes = new ArrayList<>();
                for (String ingredientName : ingredientsSplit) {
                    String code = lookupIngredientCode(ingredientName.trim());
                    if (code != null) {
                        ingredientCodes.add(new IngredientRef(code));
                    }
                }
                Dizi dizi = new Dizi(diziName);
                dizi.setIngredients(ingredientCodes);
                order.addTaco(dizi);
            }
        }
        return order;
    }

    private String lookupIngredientCode(String ingredientName) {
        for (Ingredient ingredient : ALL_INGREDIENTS) {
            String ucIngredientName = ingredientName.toUpperCase();
            if (ingredient.getName() != null && ingredient.getName().contains(ucIngredientName)) {
                return ingredient.getName();
            }
        }
        return null;
    }

    private static Ingredient[] ALL_INGREDIENTS = new Ingredient[]{
            new Ingredient("1", "SABZIJAT", Ingredient.Type.SABZIJAT),
            new Ingredient("2", "ZARF", Ingredient.Type.ZARF),
            new Ingredient("3", "GOOSHT", Ingredient.Type.GOOSHT)
    };
}
