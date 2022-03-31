# Exercise 1: Create a Password Policy 

[$LIFERAY_LEARN_YOUTUBE_URL$]=https://www.youtube.com/embed/rO8YB_WnSuc

## Exercise Goals 

- Create a New Password Policy for Mondego Employees 
- Create a New User and Change their Password to fit the Policy 

## Create a New Password Policy 

1. **Sign In** to the Mondego platform as an Administrator. 
2. **Open** the _Global Menu_. 
3. **Go to** `Control Panel` &rarr; `Security`. 
4. **Click** _Password Policies_. 
5. **Click** the _Add_ button in the top right. 
6. **Type** `Employee Password Policy` for the _Name_. 
7. **Type** `The password policy for all Mondego employees` for the description. 

## Manage Password Changes 

1. **Click** _Password Changes_ to expand the menu. 
2. **Click** the toggle beside _Changeable_ to allow password changes. 
3. **Click** _Eternal_ under _Reset Ticket Max Age_. 
	- This is the amount of time a Password Reset link is valid. 
4. **Choose** _1 Hour_. 

## Enable and Manage Syntax Checking

1. **Click** _Password Syntax Checking_ to expand the section. 
2. **Click** the toggle to _Enable Syntax Checking_. 
3. **Type** `1` under _Minimum Alpha Numeric_. 
4. **Type** `10` under _Minimum Length_. 
5. **Type** `1` under _Minimum Lower Case_. 
6. **Type** `1` under _Minimum Numbers_. 
7. **Type** `1` under _Minimum Symbols_. 
8. **Type** `1` under _Minimum Upper Case_. 

## Enable and Manage Password History 

1. **Click** _Password History_ to expand the section. 
2. **Click** the toggle to _Enable History_. 
3. **Click** the _History Count_ drop-down. 
4. **Choose** _8_. 
	- The platform will save 8 previous passwords to prevent Users from reusing old passwords. 

## Set a Password Expiration and Grace Limit 

1. **Click** _Password Expiration_ to expand the section. 
2. **Click** the toggle to _Enable Expiration_. 
3. **Click** the _Maximum Age_ drop-down. 
4. **Choose** _26 Weeks_. 
5. **Click** the _Warning Time_ drop-down. 
6. **Choose** _2 Weeks_. 
7. **Click** the _0_ under _Grace Limit_. 
8. **Type** `10`. 
	- This will allow a User to log in 10 times after their password is expired before requiring them to change passwords. 

## Enable Password Lockout 

1. **Click** _Lockout_ to expand the section. 
2. **Click** the toggle to _Enable Lockout_. 
3. **Type** `5` under _Maximum Failure_. 
4. **Click** the _Lockout Duration_ drop-down. 
5. **Choose** _30 Minutes_. 
6. **Click** _Save_. 

## Assign the Employee Password Policy to Users 

1. **Click** the _Options_ (three dots) icon next to _Employee Password Policy_. 
2. **Click** _Assign Members_. 
3. **Click** the _Add_ button at the top right. 
4. **Click** the checkbox beside _Filter and Order_ to select all Users. 
5. **Click** _Add_. 

## Assign the Employee Password Policy to an Organization 

1. **Click** the _Organizations_ tab to the right of _Users_. 
2. **Click** the _Add_ button at the top right. 
	- You should see three Organizations, Mondego Investments and its two Sub-Organizations, Investment Advisors and Investment Marketing. 
3. **Click** the checkbox next to _Mondego Investments_ to select. 
4. **Click** _Add_. 

## Create a New User 

1. **Open** the _Global Menu_. 
2. **Click** _Users and Organizations_ under _Control Panel_ &rarr; _Users_. 
3. **Click** the _Add_ button. 
4. **Type** `evie.calhoun` for the _Screen Name_. 
5. **Type** `evie.calhoun@mondego.com` for the _Email Address_. 
6. **Type** `Evie` for the _First Name_. 
7. **Type** `Calhoun` for the _Last Name_. 
8. **Click** _Save_ at the bottom of the page. 

## Give Evie Calhoun a Password 

1. **Click** _Password_ in the menu on the left. 
2. **Type** `test` for the _New Password_. 
3. **Type** `test` once more for _Enter Again_. 
4. **Click** _Save_. 

## Add Evie Calhoun to the Mondego Investments Organization 

1. **Click** _Organizations_ in the menu on the left. 
2. **Click** _Select_ beside _Organizations_. 
3. **Click** _Choose_ beside _Mondego Investments_. 
4. **Click** _Save_. 

## Sign In as Evie Calhoun and Set a New Password 

1. **Open** the _Personal Menu_. 
2. **Click** _Sign Out_. 
3. **Click** _Sign In_. 
4. **Sign In** using the email and password we just set. 
	- Email: `evie.calhoun@mondego.com` 
	- Password: `test` 
5. **Click** _I Agree_ to the _Terms of Use_. 
6. **Type** `t3st` as the _New Password_ and _Enter Again_. 
7. **Click** _Save_. 
	- You should get an error informing you that the password is too short. 
8. **Type** `investment` as the _New Password_ and _Enter Again_. 
9. **Click** _Save_. 
	- You should get an error informing you that the password needs at least 1 number. 
10. **Type** `1Nvestment!` as the _New Password_ and _Enter Again_. 
	- This password should meet all of the requirements established in the Employee Password Policy. Feel free to continue experimenting with invalid passwords to preview the error messages before saving a valid one. 
11. **Click** _Save_. 
12. **Choose** a _Password Reminder_ question. 
13. **Type** an answer. 
14. **Click** _Save_. 

---

## Bonus Exercise 

1. Add another Password Policy for Mondego Customers. Set syntax requirements, a password expiration, and enable password history and account lockout. Assign an existing User to this policy instead of the Employee Policy. 

---

## Next Up

* [Exercise 2: Enable CAPTCHA](./exercise-2-enable-captcha.md)