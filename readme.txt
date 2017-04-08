
FUND COLLECTOR(android app and api)
With the demonetization drive , there has been great advancement in e-banking in the sector of digital payments and then there was BHIM app.
We are presenting you an app that will ease the way how small scale festivals and basic collection  of money is done.
Our app ‘Fund Collector’ helps to collect fund for day to day activities and society work.
Our app has 3 sections 

->Society-It includes works like repairing of building, organizing events and festivals and many more things.  
->Government-Government projects with not enough fund could also started here and moreover people can keep a check on project so as to avoid corruption. 
->Individual- As the name suggests  it includes birthday parties and other personal occasions.

First of all in order to use the app one must register on it.After registeration you will get a code using which you could sign in. Any user or a 
group leader can initiate the request for donation and start the collection group which includes cause(full description), amount to be collected, 
etc. Max donation by any user is fixed so as to avoid corruption in case of 
government and in case of society to support collective contribution

Requirements
smartphone with android kitkat 4.4 or above to run the app.

Their are still some bugs which could be removed in a short amount of time like we haven't added the payment gateway.


develepor Team hiiii
CHETAN DASHORA(LEADER)
SIDDHARTHA AGRAWAL

Web api to provide a fund raising service:

register/:(to register user)
request type:POST
data:
{
"username":username;
"password":password;
"email":email;
}

accounts/login/:
request type:POST
data:
{
"username":username of registered person;
"password":password;
}


group_register/:(to create a new benificiary)
request type:POST
data:
{
"name":name of the benificiary or project;
"description":details of project;
"amount_required":amount;
"min_amount_req":minimum amount required;
"max_deposit_per_person":maximum deposit allowed;
"type":type of activity or whom it is concerned to;
"user":person logged in;
}


find_group/:
request type:POST
data:
{"Code":code;
"user":user;
}

submit_amount/:
request type:POST 
data:
{
"user":user logged in;
"amount":amount being paid;
"Code":code for indentifying benifeciary;
}

check_previous_status/: gives data about previous processes
request type:POST
data:
{
"user":user;
}
