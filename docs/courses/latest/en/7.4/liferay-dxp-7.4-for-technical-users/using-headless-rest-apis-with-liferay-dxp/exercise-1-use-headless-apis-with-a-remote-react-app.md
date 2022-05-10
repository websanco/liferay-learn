# Exercise 1: Use Headless APIs with a Remote React App

[$LIFERAY_LEARN_YOUTUBE_URL$]=https://www.youtube.com/embed/W_HUkCyVNaM

## Exercise Goals

- Identify Liferay DXP Features to Leverage with Headless APIs
- Integrate a Remote Application with Liferay DXP Using Headless APIs

## Open the Faria Financial Management App

1. **Open** the Faria Financial Management App at `localhost:3000`.
    - Currently, the data displaying in the app is saved with the React Application. By using Headless APIs, we will replace the App data with Liferay data from a Liferay Object.
2. **Sign In** to your Liferay instance at `localhost:8080`.
3. **Open** the _Site Menu_.
4. **Open** the _Bank Accounts_ Object under _People_.
    - The _Bank Accounts_ Object should be scoped to the Site level and placed in the _People_ section of the menu if you followed the prerequisites.

## Identify the Bank Accounts Headless API

1. **Open** the Liferay API Explorer at `localhost:8080/o/api`.
2. **Click** the _REST Applications_ dropdown to see a list of APIs that can be called.
3. **Click** the `c/bankaccounts` Headless API.
    - Note the reference is `http://localhost:8080/o/c/bankaccounts`. The Headless APIs for all Objects will automatically have the `c/` prefix followed by the Object name. This name is assigned when Liferay generates the Headless API.

## Create the Account1.js, Account2.js, and Account3.js Files

1. **Go to** the `src` folder.
2. **Create** three new files called `Account1.js`, `Account2.js`, and `Account3.js`.

## Add Code to the Account1.js File

1. **Open** the `Account1.js` file in your editor.
2. **Copy and Paste** the code snippet below into `Account1.js`:
	
```js
import React from 'react';
import './style.css';

//Fetches data from the Headless API automatically generated when creating the Bank Accounts Liferay Object. 
//This file corresponds to the checking account.

class Account1 extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      accountBalance: null,
      accountNumber: null,
      accountType: null
    };
  }

  componentDidMount() {
    fetch('/o/c/bankaccounts/42744', {
      method: 'GET',
      headers: {
        Authorization: 'Basic ' + btoa('test@liferay.com:test'),
        'Content-Type': 'application/json'
      }})
      .then(res => res.json())
      .then(
        (result) => {
          this.setState({
            isLoaded: true,
            accountBalance: result.accountBalance,
            accountNumber: result.accountNumber,
            accountType: result.accountType
          });
        },
        // Note: it's important to handle errors here
        // instead of a catch() block so that we don't swallow
        // exceptions from actual bugs in components.
        (error) => {
          this.setState({
            isLoaded: true,
            error
          });
        }
      )
  }

  render() {
    const { error, isLoaded, accountBalance, accountNumber, accountType} = this.state;
    if (error) {
      return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading...</div>;
    } else {
      return (
        <div className="Dashboard">
            <h2>{accountType.name} Account {accountNumber}</h2>
            <h3>${accountBalance}</h3>
        </div>
      );
    }
  }
}

export default Account1;
```	

## Add Code to the Account2.js File

1. **Open** the `Account2.js` file in your editor.
2. **Copy and Paste** the code snippet below into `Account2.js`:

```js
import React from 'react';
import './style.css';

//Fetches data from the Headless API automatically generated when creating the Bank Accounts Liferay Object. 
//This file corresponds to the savings account.

class Account2 extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      accountBalance: null,
      accountNumber: null,
      accountType: null
    };
  }

  componentDidMount() {
    fetch('/o/c/bankaccounts/42746', {
      method: 'GET',
      headers: {
        Authorization: 'Basic ' + btoa('test@liferay.com:test'),
        'Content-Type': 'application/json'
      }})
      .then(res => res.json())
      .then(
        (result) => {
          this.setState({
            isLoaded: true,
            accountBalance: result.accountBalance,
            accountNumber: result.accountNumber,
            accountType: result.accountType
          });
        },
        // Note: it's important to handle errors here
        // instead of a catch() block so that we don't swallow
        // exceptions from actual bugs in components.
        (error) => {
          this.setState({
            isLoaded: true,
            error
          });
        }
      )
  }

  render() {
    const { error, isLoaded, accountBalance, accountNumber, accountType} = this.state;
    if (error) {
      return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading...</div>;
    } else {
      return (
        <div className="Dashboard">
            <h2>{accountType.name} Account {accountNumber}</h2>
            <h3>${accountBalance}</h3>
        </div>
      );
    }
  }
}

export default Account2;
```

## Add Code to the Account3.js File

1. **Open** the `Account3.js` file in your editor.
2. **Copy and Paste** the code snippet below into `Account3.js`:

```js
import React from 'react';
import './style.css';

//Fetches data from the Headless API automatically generated when creating the Bank Accounts Liferay Object. 
//This file corresponds to the retirement account.

class Account3 extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      accountBalance: null,
      accountNumber: null,
      accountType: null
    };
  }

  componentDidMount() {
    fetch('/o/c/bankaccounts/42748', {
      method: 'GET',
      headers: {
        Authorization: 'Basic ' + btoa('test@liferay.com:test'),
        'Content-Type': 'application/json'
      }})
      .then(res => res.json())
      .then(
        (result) => {
          this.setState({
            isLoaded: true,
            accountBalance: result.accountBalance,
            accountNumber: result.accountNumber,
            accountType: result.accountType
          });
        },
        // Note: it's important to handle errors here
        // instead of a catch() block so that we don't swallow
        // exceptions from actual bugs in components.
        (error) => {
          this.setState({
            isLoaded: true,
            error
          });
        }
      )
  }

  render() {
    const { error, isLoaded, accountBalance, accountNumber, accountType} = this.state;
    if (error) {
      return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading...</div>;
    } else {
      return (
        <div className="Dashboard">
            <h2>{accountType.name} Account {accountNumber}</h2>
            <h3>${accountBalance}</h3>
        </div>
      );
    }
  }
}

export default Account3;
```

## Add Imports to the Accounts.js File

1. **Open** the `Accounts.js` file.
2. **Add** imports for the three Accounts Class Objects you just created:

```js
import Account1 from './Account1';
import Account2 from './Account2';
import Account3 from './Account3';
```

## Add Account Classes to the Carousel

1. **Go to** the Carousel section in the `Accounts.js` file.
2. **Delete** the placeholder data under each `Carousel.Item`.
3. **Replace** with the three Account Classes:

```js
        <Carousel>
          <Carousel.Item interval={15000}>
            <Account1 />
            
          </Carousel.Item>
          <Carousel.Item interval={15000}>
            <Account2 />
            
          </Carousel.Item>
          <Carousel.Item interval={15000}>
            <Account3 />
            
          </Carousel.Item>
          
        </Carousel>
```

4. **Open** the App in your browser to see the changes.

## Test the Application

1. **Go to** the _Bank Accounts_ Object in your Liferay instance.
2. **Click** the ID of one of the entries.
3. **Change** the Account Balance.
4. **Refresh** the App to view the changes.

---

## Bonus Exercise

1. Add a new Financial Account entry for your Administrator. Adjust the app’s code to display the new entity in the account carousel and test the application.

## Next Up

* [Summary](./summary.md)

## Previous Step

* [Producing APIs with REST Builder](./producing-apis-with-rest-builder.md)
