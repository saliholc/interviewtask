TRIP PLANNING 
  
  REST API for trip planing of Angel.

  # URL
  
  /trip/start/:country/budgetPerCountry/:budgetPerCountry/totalBudget/:totalBudget/currency/:currency 
    
  # Method
    
    GET 
 
  # URL Params
      
    Required:
      country=[String]
      budgetPerCountry=[Integer]
      totalBudget=[Integer]
      currency=[String] 
       
   # Data Params
     None

  # Success Response
     Code : 200
     Content :
        [
		    "<Country> has <count> neighbour countries (.....) and Angel can travel around them <times> times.",
		    "He will have 200 EUR leftover.",
		    "for <neigbour> he will need to buy 200,00 EUR",
		    .....................................
		]
		
   # Error Response:
	
	 Code: 404 NOT FOUND		
		         
   # Sample Call
     
      curl -X GET --header 'Accept: application/json' 'http://localhost:8000/trip/start/:COUNTRY/budgetPerCountry/:BUDGETPERCOUNTRY/totalBudget/:TOTLBUDGET/currency/:CURRENCY'     
    