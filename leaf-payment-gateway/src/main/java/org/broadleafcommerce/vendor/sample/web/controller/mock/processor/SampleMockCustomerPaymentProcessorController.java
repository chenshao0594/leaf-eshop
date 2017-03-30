/*
 * #%L
 * BroadleafCommerce Sample Payment Gateway
 * %%
 * Copyright (C) 2009 - 2015 Broadleaf Commerce
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.broadleafcommerce.vendor.sample.web.controller.mock.processor;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.CreditCardValidator;
import org.broadleafcommerce.vendor.sample.service.payment.SamplePaymentGatewayConstants;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

@Controller("blSampleMockCustomerPaymentProcessorController")
public class SampleMockCustomerPaymentProcessorController {

    @RequestMapping(value = "/sample-customer-payment/process", method = RequestMethod.POST)
    public @ResponseBody String processTransparentRedirectForm(HttpServletRequest request){
        Map<String,String[]> paramMap = request.getParameterMap();

        String returnUrl="";
        String customerId="";
        String billingFirstName = "";
        String billingLastName = "";
        String billingAddressLine1 = "";
        String billingAddressLine2 = "";
        String billingCity = "";
        String billingState = "";
        String billingZip = "";
        String billingCountry = "";
        String shippingFirstName = "";
        String shippingLastName = "";
        String shippingAddressLine1 = "";
        String shippingAddressLine2 = "";
        String shippingCity = "";
        String shippingState = "";
        String shippingZip = "";
        String shippingCountry = "";
        String emailAddress = "";
        String companyName = "";
        String billingPhone = "";
        String creditCardName = "";
        String creditCardNumber = "";
        String creditCardExpDate = "";
        String creditCardCVV = "";
        String cardType = "UNKNOWN";

        String resultMessage = "";
        String resultSuccess = "";
        String paymentTokenId = UUID.randomUUID().toString();

        if (paramMap.get(SamplePaymentGatewayConstants.TRANSPARENT_REDIRECT_RETURN_URL) != null
                && paramMap.get(SamplePaymentGatewayConstants.TRANSPARENT_REDIRECT_RETURN_URL).length > 0) {
            returnUrl = paramMap.get(SamplePaymentGatewayConstants.TRANSPARENT_REDIRECT_RETURN_URL)[0];
        }

        if (paramMap.get(SamplePaymentGatewayConstants.CUSTOMER_ID) != null
                && paramMap.get(SamplePaymentGatewayConstants.CUSTOMER_ID).length > 0) {
            customerId = paramMap.get(SamplePaymentGatewayConstants.CUSTOMER_ID)[0];
        }

        if (paramMap.get(SamplePaymentGatewayConstants.BILLING_FIRST_NAME) != null
                && paramMap.get(SamplePaymentGatewayConstants.BILLING_FIRST_NAME).length > 0) {
            billingFirstName = paramMap.get(SamplePaymentGatewayConstants.BILLING_FIRST_NAME)[0];
        }

        if (paramMap.get(SamplePaymentGatewayConstants.BILLING_LAST_NAME) != null
                && paramMap.get(SamplePaymentGatewayConstants.BILLING_LAST_NAME).length > 0) {
            billingLastName = paramMap.get(SamplePaymentGatewayConstants.BILLING_LAST_NAME)[0];
        }

        if (paramMap.get(SamplePaymentGatewayConstants.BILLING_ADDRESS_LINE1) != null
                && paramMap.get(SamplePaymentGatewayConstants.BILLING_ADDRESS_LINE1).length > 0) {
            billingAddressLine1 = paramMap.get(SamplePaymentGatewayConstants.BILLING_ADDRESS_LINE1)[0];
        }

        if (paramMap.get(SamplePaymentGatewayConstants.BILLING_ADDRESS_LINE2) != null
                && paramMap.get(SamplePaymentGatewayConstants.BILLING_ADDRESS_LINE2).length > 0) {
            billingAddressLine2 = paramMap.get(SamplePaymentGatewayConstants.BILLING_ADDRESS_LINE2)[0];
        }

        if (paramMap.get(SamplePaymentGatewayConstants.BILLING_CITY) != null
                && paramMap.get(SamplePaymentGatewayConstants.BILLING_CITY).length > 0) {
            billingCity = paramMap.get(SamplePaymentGatewayConstants.BILLING_CITY)[0];
        }

        if (paramMap.get(SamplePaymentGatewayConstants.BILLING_STATE) != null
                && paramMap.get(SamplePaymentGatewayConstants.BILLING_STATE).length > 0) {
            billingState = paramMap.get(SamplePaymentGatewayConstants.BILLING_STATE)[0];
        }

        if (paramMap.get(SamplePaymentGatewayConstants.BILLING_ZIP) != null
                && paramMap.get(SamplePaymentGatewayConstants.BILLING_ZIP).length > 0) {
            billingZip = paramMap.get(SamplePaymentGatewayConstants.BILLING_ZIP)[0];
        }

        if (paramMap.get(SamplePaymentGatewayConstants.BILLING_COUNTRY) != null
                && paramMap.get(SamplePaymentGatewayConstants.BILLING_COUNTRY).length > 0) {
            billingCountry = paramMap.get(SamplePaymentGatewayConstants.BILLING_COUNTRY)[0];
        }

        if (paramMap.get(SamplePaymentGatewayConstants.SHIPPING_FIRST_NAME) != null
                && paramMap.get(SamplePaymentGatewayConstants.SHIPPING_FIRST_NAME).length > 0) {
            shippingFirstName = paramMap.get(SamplePaymentGatewayConstants.SHIPPING_FIRST_NAME)[0];
        }

        if (paramMap.get(SamplePaymentGatewayConstants.SHIPPING_LAST_NAME) != null
                && paramMap.get(SamplePaymentGatewayConstants.SHIPPING_LAST_NAME).length > 0) {
            shippingLastName = paramMap.get(SamplePaymentGatewayConstants.SHIPPING_LAST_NAME)[0];
        }

        if (paramMap.get(SamplePaymentGatewayConstants.SHIPPING_ADDRESS_LINE1) != null
                && paramMap.get(SamplePaymentGatewayConstants.SHIPPING_ADDRESS_LINE1).length > 0) {
            shippingAddressLine1 = paramMap.get(SamplePaymentGatewayConstants.SHIPPING_ADDRESS_LINE1)[0];
        }

        if (paramMap.get(SamplePaymentGatewayConstants.SHIPPING_ADDRESS_LINE2) != null
                && paramMap.get(SamplePaymentGatewayConstants.SHIPPING_ADDRESS_LINE2).length > 0) {
            shippingAddressLine2 = paramMap.get(SamplePaymentGatewayConstants.SHIPPING_ADDRESS_LINE2)[0];
        }

        if (paramMap.get(SamplePaymentGatewayConstants.SHIPPING_CITY) != null
                && paramMap.get(SamplePaymentGatewayConstants.SHIPPING_CITY).length > 0) {
            shippingCity = paramMap.get(SamplePaymentGatewayConstants.SHIPPING_CITY)[0];
        }

        if (paramMap.get(SamplePaymentGatewayConstants.SHIPPING_STATE) != null
                && paramMap.get(SamplePaymentGatewayConstants.SHIPPING_STATE).length > 0) {
            shippingState = paramMap.get(SamplePaymentGatewayConstants.SHIPPING_STATE)[0];
        }

        if (paramMap.get(SamplePaymentGatewayConstants.SHIPPING_ZIP) != null
                && paramMap.get(SamplePaymentGatewayConstants.SHIPPING_ZIP).length > 0) {
            shippingZip = paramMap.get(SamplePaymentGatewayConstants.SHIPPING_ZIP)[0];
        }

        if (paramMap.get(SamplePaymentGatewayConstants.SHIPPING_COUNTRY) != null
                && paramMap.get(SamplePaymentGatewayConstants.SHIPPING_COUNTRY).length > 0) {
            shippingCountry = paramMap.get(SamplePaymentGatewayConstants.SHIPPING_COUNTRY)[0];
        }

        if (paramMap.get(SamplePaymentGatewayConstants.BILLING_PHONE) != null
                && paramMap.get(SamplePaymentGatewayConstants.BILLING_PHONE).length > 0) {
            billingPhone = paramMap.get(SamplePaymentGatewayConstants.BILLING_PHONE)[0];
        }
        
        if (paramMap.get(SamplePaymentGatewayConstants.BILLING_EMAIL) != null
                && paramMap.get(SamplePaymentGatewayConstants.BILLING_EMAIL).length > 0) {
            emailAddress = paramMap.get(SamplePaymentGatewayConstants.BILLING_EMAIL)[0];
        }
        
        if (paramMap.get(SamplePaymentGatewayConstants.BILLING_COMPANY_NAME) != null
                && paramMap.get(SamplePaymentGatewayConstants.BILLING_COMPANY_NAME).length > 0) {
            companyName = paramMap.get(SamplePaymentGatewayConstants.BILLING_COMPANY_NAME)[0];
        } 

        if (paramMap.get(SamplePaymentGatewayConstants.CREDIT_CARD_NAME) != null
                && paramMap.get(SamplePaymentGatewayConstants.CREDIT_CARD_NAME).length > 0) {
            creditCardName = paramMap.get(SamplePaymentGatewayConstants.CREDIT_CARD_NAME)[0];
        }

        if (paramMap.get(SamplePaymentGatewayConstants.CREDIT_CARD_NUMBER) != null
                && paramMap.get(SamplePaymentGatewayConstants.CREDIT_CARD_NUMBER).length > 0) {
            creditCardNumber = paramMap.get(SamplePaymentGatewayConstants.CREDIT_CARD_NUMBER)[0];
        }

        if (paramMap.get(SamplePaymentGatewayConstants.CREDIT_CARD_EXP_DATE) != null
                && paramMap.get(SamplePaymentGatewayConstants.CREDIT_CARD_EXP_DATE).length > 0) {
            creditCardExpDate = paramMap.get(SamplePaymentGatewayConstants.CREDIT_CARD_EXP_DATE)[0];
        }

        if (paramMap.get(SamplePaymentGatewayConstants.CREDIT_CARD_CVV) != null
                && paramMap.get(SamplePaymentGatewayConstants.CREDIT_CARD_CVV).length > 0) {
            creditCardCVV = paramMap.get(SamplePaymentGatewayConstants.CREDIT_CARD_CVV)[0];
        }

        CreditCardValidator visaValidator = new CreditCardValidator(CreditCardValidator.VISA);
        CreditCardValidator amexValidator = new CreditCardValidator(CreditCardValidator.AMEX);
        CreditCardValidator mcValidator = new CreditCardValidator(CreditCardValidator.MASTERCARD);
        CreditCardValidator discoverValidator = new CreditCardValidator(CreditCardValidator.DISCOVER);

        if (StringUtils.isNotBlank(creditCardNumber) &&
                StringUtils.isNotBlank(creditCardExpDate)) {

            boolean validCard = false;
            if (visaValidator.isValid(creditCardNumber)){
                validCard = true;
                cardType = "VISA";
            } else if (amexValidator.isValid(creditCardNumber)) {
                validCard = true;
                cardType = "AMEX";
            } else if (mcValidator.isValid(creditCardNumber)) {
                validCard = true;
                cardType = "MASTERCARD";
            } else if (discoverValidator.isValid(creditCardNumber)) {
                validCard = true;
                cardType = "DISCOVER";
            }

            boolean validDateFormat = false;
            boolean validDate = false;
            String[] parsedDate = creditCardExpDate.split("/");
            if (parsedDate.length == 2) {
                String expMonth = parsedDate[0];
                String expYear = parsedDate[1];
                try {
                    DateTime expirationDate = new DateTime(Integer.parseInt("20"+expYear), Integer.parseInt(expMonth), 1, 0, 0);
                    expirationDate = expirationDate.dayOfMonth().withMaximumValue();
                    validDate = expirationDate.isAfterNow();
                    validDateFormat = true;
                } catch (Exception e) {
                    //invalid date format
                }
            }

            if (!validDate || !validDateFormat) {
                resultMessage = "customer.payment.expiration.invalid";
                resultSuccess = "false";
            } else if (!validCard) {
                resultMessage = "customer.payment.card.invalid";
                resultSuccess = "false";
            } else {
                resultMessage = "Success!";
                resultSuccess = "true";
            }

        } else {
            resultMessage = "customer.payment.invalid";
            resultSuccess = "false";
        }

        StringBuffer response = new StringBuffer();
        response.append("<!DOCTYPE HTML>");
        response.append("<!--[if lt IE 7]> <html class=\"no-js lt-ie9 lt-ie8 lt-ie7\" lang=\"en\"> <![endif]-->");
        response.append("<!--[if IE 7]> <html class=\"no-js lt-ie9 lt-ie8\" lang=\"en\"> <![endif]-->");
        response.append("<!--[if IE 8]> <html class=\"no-js lt-ie9\" lang=\"en\"> <![endif]-->");
        response.append("<!--[if gt IE 8]><!--> <html class=\"no-js\" lang=\"en\"> <!--<![endif]-->");
        response.append("<body>");
        response.append("<form action=\"" +
                returnUrl +
                "\" method=\"POST\" id=\"SamplePaymentGatewayRedirectForm\" name=\"SamplePaymentGatewayRedirectForm\">");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.CUSTOMER_ID
                +"\" value=\"" + customerId + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.RESULT_MESSAGE
                +"\" value=\"" + resultMessage + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.RESULT_SUCCESS
                +"\" value=\"" + resultSuccess + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.BILLING_FIRST_NAME
                +"\" value=\"" + billingFirstName + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.BILLING_LAST_NAME
                +"\" value=\"" + billingLastName + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.BILLING_ADDRESS_LINE1
                +"\" value=\"" + billingAddressLine1 + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.BILLING_ADDRESS_LINE2
                +"\" value=\"" + billingAddressLine2 + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.BILLING_CITY
                +"\" value=\"" + billingCity + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.BILLING_STATE
                +"\" value=\"" + billingState + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.BILLING_ZIP
                +"\" value=\"" + billingZip + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.BILLING_COUNTRY
                +"\" value=\"" + billingCountry + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.SHIPPING_FIRST_NAME
                +"\" value=\"" + shippingFirstName + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.SHIPPING_LAST_NAME
                +"\" value=\"" + shippingLastName + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.SHIPPING_ADDRESS_LINE1
                +"\" value=\"" + shippingAddressLine1 + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.SHIPPING_ADDRESS_LINE2
                +"\" value=\"" + shippingAddressLine2 + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.SHIPPING_CITY
                +"\" value=\"" + shippingCity + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.SHIPPING_STATE
                +"\" value=\"" + shippingState + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.SHIPPING_ZIP
                +"\" value=\"" + shippingZip + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.SHIPPING_COUNTRY
                +"\" value=\"" + shippingCountry + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.BILLING_PHONE
                +"\" value=\"" + billingPhone + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.BILLING_EMAIL
                +"\" value=\"" + emailAddress + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.BILLING_COMPANY_NAME
                +"\" value=\"" + companyName + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.CREDIT_CARD_NAME
                +"\" value=\"" + creditCardName + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.CREDIT_CARD_LAST_FOUR
                +"\" value=\"" + StringUtils.right(creditCardNumber, 4) + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.CREDIT_CARD_TYPE
                +"\" value=\"" + cardType + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.CREDIT_CARD_EXP_DATE
                +"\" value=\"" + creditCardExpDate + "\"/>");
        response.append("<input type=\"hidden\" name=\"" + SamplePaymentGatewayConstants.PAYMENT_TOKEN_ID
                +"\" value=\"" + paymentTokenId + "\"/>");

        response.append("<input type=\"submit\" value=\"Please Click Here To Complete New Payment\"/>");
        response.append("</form>");
        response.append("<script type=\"text/javascript\">");
        response.append("document.getElementById('SamplePaymentGatewayRedirectForm').submit();");
        response.append("</script>");
        response.append("</body>");
        response.append("</html>");

        return response.toString();
    }
}
