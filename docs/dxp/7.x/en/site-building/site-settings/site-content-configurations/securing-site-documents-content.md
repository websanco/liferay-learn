# Securing Site Documents Content

By default, your Site's Documents and Media files and folders are not visible in the web server directory index, so you can't view them through the browser. To enable browsing a Site's documents through the web server directory index, you can enable this setting for your Site.

## Configuring Document Visibility in the Web Server Directory Index

1. Open the Product Menu and go to *Configuration* &rarr; *Settings* (previously *Site Settings*).
1. Select the *General* tab and scroll down to the *Documents and Media* sub-heading and expand it.
1. Toggle the *Enable Directory Indexing* setting between *YES* and *NO* to enable/disable the feature.

    ![You can secure your Site's documents and media through the General Site settings.](./securing-site-documents-content/images/01.png)

    ```important::
      Once enabled, only Administrators and Users with view permission can browse your Site's Documents and Media files in the web server directory index.
    ```

1. Scroll down and click *Save* to apply the changes.

Once enabled, your Site's Documents and Media files can be browsed at `http://localhost:8080/documents/site-name` (e.g. `http://localhost:8080/documents/marketing` for a Site called *Marketing*).
