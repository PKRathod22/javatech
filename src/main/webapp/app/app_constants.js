app.constant("appConstant",{
	appName : "My App",
    weightValExpr : "^[+]?([0-9]{0,11}(?:[\.][0-9]{0,2})?|\.[0-9]+)$",
    piecesValExpr : "^[+]?([0-9]{0,11}(?:[\.][0-9]{0,0})?|\.[0-9]+)$",
    freeText100CharsExpr : "[ A-Za-z0-9\n_@.#&+-]{0,100}",
    freeText250CharsExpr : "[ A-Za-z0-9\n_@.#&+-]{0,250}",
    freeText4000CharsExpr : "[ A-Za-z0-9\n_@.#&+-]{0,4000}",
    alphaNumeric10CharsExpr : "^[ A-Za-z0-9]{0,10}$",
    alphaNumeric30CharsExpr : "^[ A-Za-z0-9]{0,30}$",
    alphaNumeric50CharsExpr : "^[ A-Za-z0-9]{0,50}$",
    alphaNumeric100CharsExpr : "^[ A-Za-z0-9]{0,100}$",
    alphaNumeric250CharsExpr : "^[ A-Za-z0-9]{0,250}$",
    percentageExpr : "^[0-9.]{0,100}$",
    chargeNumberOfUnitList : ["-45", "+45", "+100", "+250", "+500", "+1000"],
    multipleEmailExpr : /^(([^<>()\[\]\\.,,:\s@"]+(\.[^<>()\[\]\\.,,:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
    testExpr : "sandi",
    
    
    
    /*Regular Expression - Start Here*/
    
    REG_EXP_MOBILE_NUMBER : "mobile.number",
  	REG_EXP_PHONE_NUMBER : "phone.number",
  	REG_EXP_MULTIPLE_EMAIL : "multiple.email",
  	


    /*Regular Expression - End Here*/
});
