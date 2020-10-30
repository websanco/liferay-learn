# API Headers Reference

This article documents the available headers you can use when making Headless API requests:

* [`Accept`](#accept)
* [`Accept-Language`](#accept-language)
* [`Authorization`](#authorization)
* [`Content-Type`](#content-type)
* [`Cookie`](#cookie)

## `Accept`

Indicates what format the response content should take. Defaults to `json`.

### Valid Options

* `json`
* `xml`

### Example

```bash
curl --header 'Accept: application/xml' 'example.com/o/headless-admin-user/v1.0/user-accounts'
```

## `Accept-Language`

For resources containing content translated into multiple languages, indicates the language for the returned response content. 

### Valid Options

All valid language tags (an ISO-639 language identifier plus ISO-3166-1 alpha-2 country identifier); e.g. `en-US`, `es-ES`. Defaults to the default language of the site that houses the requested content.

### Example

```
curl --header 'Accept-Language: pt-BR' 'example.com/o/headless-delivery/v1.0/sites/20124/blog-postings'
```

## `Authorization`

Identifies which User is making the request, using DXP's Authentication framework. (See also [`Cookie`](#cookie).) If both this and `Cookie` are not supplied, the request is attempted as a guest (unauthenticated) user.

### Valid Options

* `Basic` + Base64-encoded credentials
* `Bearer` + OAuth token

### Example

```
curl --header 'Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQK' 'example.com/o/headless-admin-user/v1.0/user-accounts'
```

## `Content-Type`

Allows uploading binary data.

### Valid Options

* `multipart/form-data`

### Example

```
curl --form 'file=@myfile.txt' --header 'Content-Type: multipart/form-data; boundary=ARBITRARY' 'example.com'
```

## `Cookie`

Identifies the User making the request, using DXP's Authentication framework. (See also [`Authorization`](#authorization).) If both this and `Authorization` are not supplied, the request is attempted as a guest (unauthenticated) user.

### Valid Options

* `JSESSIONID`

### Example

```
curl --header 'Cookie: JSESSIONID=6349351B37C3EE1F6BA4E128107E9A34' 'example.com/o/headless-admin-user/v1.0/user-accounts'
```

## `X-Accept-All-Languages`

Indicates that the server should return the requested content in all available languages. (See also [`Accept-Language`](#accept-language).)

### Valid Options

* `true`
* `false`

### Example

```
curl --header 'X-Accept-All-Languages: true' 'example.com/o/headless-admin-user/v1.0/user-accounts'
```
