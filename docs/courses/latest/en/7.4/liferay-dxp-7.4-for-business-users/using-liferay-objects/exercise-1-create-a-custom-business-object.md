# Exercise 1: Create a Custom Business Object 

[$LIFERAY_LEARN_YOUTUBE_URL$]=https://www.youtube.com/embed/wjqXrN7-SKg

## Exercise Goals 

- Create a Liferay Object for New Accounts 
- Create a Liferay Object for PTO Requests 

## Create a Picklist for Account Types 

1. **Sign in** as an Administrator. 
2. **Go to** `Control Panel` &rarr; `Object` &rarr; `Picklists` in the _Global Menu_. 
3. **Click** the _Add_ icon in the upper right. 
4. **Type** `Account Type` for _Name_. 
5. **Click** _Save_. 
6. **Click** the new _Account Type_ picklist. 
7. **Click** the _Add_ icon. 
8. **Type** `Checking` for _Name_. 
9. **Click** _Save_. 
10. **Click** the _Add_ icon. 
11. **Type** `Savings` for _Name_. 
12. **Click** _Save_. 
13. **Click** the _Add_ icon. 
14. **Type** `Individual Retirement` for _Name_. 
15. **Click** _Save_. 
16. **Click** _Save_. 

## Create a Liferay Object for New Accounts 

1. **Go to** `Control Panel` &rarr; `Object` &rarr; `Objects` in the _Global Menu_. 
2. **Click** the _Add_ icon. 
3. **Type** `New Account` for _Label_. 
4. **Type** `New Accounts` for _Plural Label_. 
5. **Click** _Save_. 
6. **Click** _New Account_. 
7. **Click** the arrows under _Scope_. 
8. **Choose** _Site_. 
9. **Click** the arrows under _Panel Category Key_. 
10. **Choose** `Site Administration` &rarr; `People`. 
11. **Click** _Save_. 

## Add Initial Fields to the New Accounts Object 

1. **Click** the _Fields_ tab at the top of the page. 
2. **Click** the _Add_ icon. 
3. **Type** `Account Holder` for _Label_. 
4. **Choose** _String_ for _Type_. 
5. **Click** the slider next to _Mandatory_. 
6. **Click** _Save_. 
7. **Click** the _Add_ icon. 
8. **Type** `Email` for _Label_. 
9. **Choose** _String_ for _Type_. 
10. **Click** the slider next to _Mandatory_. 
11. **Click** _Save_. 
12. **Click** the _Add_ icon. 
13. **Type** `Phone Number` for _Label_. 
14. **Choose** _Long_ for _Type_. 
15. **Click** the slider next to _Mandatory_. 
16. **Click** _Save_. 

## Add Remaining Fields to the New Accounts Object 

1. **Click** the _Add_ icon. 
2. **Type** `Account Type` for _Label_. 
3. **Choose** _Picklist_ for _Type_. 
4. **Choose** _Account Type_ for _Picklist_. 
5. **Click** the slider next to _Mandatory_. 
6. **Click** _Save_. 
7. **Click** the _Add_ icon. 
8. **Type** `Initial Balance` for _Label_. 
9. **Choose** _BigDecimal_ for _Type_. 
10. **Click** the slider next to _Mandatory_. 
11. **Click** _Save_. 
12. **Click** the _Add_ icon. 
13. **Type** `Date of Request` for _Label_. 
14. **Choose** _Date_ for _Type_. 
15. **Click** the slider next to _Mandatory_. 
16. **Click** _Save_. 
17. **Click** the _Details_ tab. 
18. **Click** _Publish_. 

## Add Entries for the New Accounts Object 

1. **Open** the Global Menu. 
2. **Click** on the _Mondego Group_ site. 
3. **Go to** `People` &rarr; `New Accounts` in the _Site Administration Panel_. 
4. **Click** the _Add_ icon. 
	- Refresh the page if it takes a while to load. 
5. **Type** `Mercedes Dantes` for _Account Holder_. 
6. **Choose** _Checking_ for _Account Type_. 
7. **Choose** today's date for _Date of Request_. 
8. **Type** `mdantes@gmail.com` for _Email_. 
9. **Type** `2500.00` for _Initial Balance_. 
10. **Type** `5557879546` for _Phone Number_. 
11. **Click** _Save_. 
12. **Click** the _Add_ icon. 
13. **Type** `Albert Smith` for _Account Holder_. 
14. **Choose** _Savings_ for _Account Type_. 
15. **Choose** today's date for _Date of Request_. 
16. **Type** `asmith@faria.com` for _Email_. 
17. **Type** `1780.45` for _Initial Balance_. 
18. **Type** `5556069364` for _Phone Number_. 
19. **Click** _Save_. 

## Create a Picklist for Mondego Departments 

1. **Go to** `Control Panel` &rarr; `Object` &rarr; `Picklists` in the _Global Menu_. 
2. **Click** the _Add_ icon in the upper right. 
3. **Type** `Mondego Departments` for _Name_. 
4. **Click** _Save_. 
5. **Click** _Mondego Departments_. 
6. **Click** the _Add_ icon. 
7. **Type** `Banking` for _Name_. 
8. **Click** _Save_. 
9. **Click** the _Add_ icon. 
10. **Type** `Financial Security` for _Name_. 
11. **Click** _Save_. 
12. **Click** the _Add_ icon. 
13. **Type** `Investments` for _Name_. 
14. **Click** _Save_. 
15. **Click** the _Add_ icon. 
16. **Type** `Marketing` for _Name_. 
17. **Click** _Save_. 
18. **Click** the _Add_ icon. 
19. **Type** `Design` for _Name_. 
20. **Click** _Save_. 
21. **Click** _Save_. 

## Create a Liferay Object for PTO Requests 

1. **Go to** `Control Panel` &rarr; `Object` &rarr; `Objects` in the _Global Menu_. 
2. **Click** the _Add_ icon. 
3. **Type** `PTO Request` for _Label_. 
4. **Type** `PTO Requests` for _Plural Label_. 
5. **Click** _Save_. 
6. **Click** _PTO Request_. 
7. **Click** the arrows under _Scope_. 
8. **Choose** _Site_. 
9. **Click** the arrows under _Panel Category Key_. 
10. **Choose** `Site Administration` &rarr; `People`. 
11. **Click** _Save_. 

## Add Initial Fields to the PTO Requests Object 

1. **Click** the _Fields_ tab at the top of the page. 
2. **Click** the _Add_ icon. 
3. **Type** `Name` for _Label_. 
4. **Choose** _String_ for _Type_. 
5. **Click** the slider next to _Mandatory_. 
6. **Click** _Save_. 
7. **Click** the _Add_ icon. 
8. **Type** `Department` for _Label_. 
9. **Choose** _Picklist_ for _Type_. 
10. **Choose** _Mondego Departments_ for _Picklist_. 
11. **Click** the slider next to _Mandatory_. 
12. **Click** _Save_. 

## Add Remaining Fields to the PTO Requests Object 

1. **Click** the _Add_ icon. 
2. **Type** `Start Date` for _Label_. 
3. **Choose** _Date_ for _Type_. 
4. **Click** the slider next to _Mandatory_. 
5. **Click** _Save_. 
6. **Click** the _Add_ icon. 
7. **Type** `End Date` for _Label_. 
8. **Choose** _Date_ for _Type_. 
9. **Click** the slider next to _Mandatory_. 
10. **Click** _Save_. 
11. **Click** the _Add_ icon. 
12. **Type** `Duration` for _Label_. 
13. **Choose** _String_ for _Type_. 
14. **Click** the slider next to _Mandatory_. 
15. **Click** _Save_. 
16. **Click** the _Details_ tab. 
17. **Click** _Publish_. 

## Add Entries for the PTO Requests Object 

1. **Open** the Global Menu. 
2. **Click** on the _Mondego Group_ site. 
3. **Go to** `People` &rarr; `New Accounts` in the _Site Administration Panel_. 
4. **Click** the _Add_ icon. 
5. **Choose** _Marketing_ for _Department_. 
6. **Type** `16.00h` for _Duration_. 
7. **Choose** today's date for _Start Date_. 
8. **Choose** tomorrow's date for _End Date_. 
9. **Type** `Naomi Engel` for _Name_. 
	* As we did not specify a particular layout for this Object, these fields may appear in varying orders. 
10. **Click** _Save_. 
11. **Click** the _Add_ icon. 
12. **Choose** _Banking_ for _Department_. 
13. **Type** `8.00h` for _Duration_. 
14. **Choose** a date next week for _Start Date_. 
15. **Choose** the same date for _End Date_. 
16. **Type** `Bethany Park` for _Name_. 
17. **Click** _Save_. 

---

## Bonus Exercise 

1. Create a new Liferay Object for Account Activity. Be sure to include fields for Customer Name, Transaction Amount, Account Number, and Account Type. 

---

## Next Up

* [Integrating and Displaying Liferay Objects](./integrating-and-displaying-liferay-objects.md)

## Previous Step

* [Creating New Objects](./creating-new-objects.md)