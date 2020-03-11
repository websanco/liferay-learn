#!/usr/bin/expect -f

set timeout -1

spawn yo liferay-theme:classic

expect "What would you like to call your theme?"
send -- "Classic with Blue Background\r"
expect "What id would you like to give to your theme?"
send -- "liferay-5b2v\r"
expect "Which version of Liferay is this theme for?"
send -- "\r"
expect "Would you like to add Font Awesome to your theme?"
send -- "n\r"
expect "Select your deployment strategy"
send \x03
expect eof