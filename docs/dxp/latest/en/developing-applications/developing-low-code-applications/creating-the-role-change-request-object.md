# Creating the Role Change Request Object

> **Subscribers**

Consider a Human Resources application that collects employee requests to change Roles within the software engineering department: the Role Change Request App. This app starts with the employee submitting a request form. Processing then proceeds to the Current Manager, then to the Receiving Manager, and finally to Human Resources. At each step a different form is presented, and the object accumulates data. By the end of the process the whole data object is complete.

![The Role Change Request object flows through several steps in this Workflow Powered App.](./creating-the-role-change-request-object/images/01.png)

Here you'll create the backing object, form views, and table views. See [Creating the Role Change Request App](./creating-the-role-change-request-app.md) to create the Workflow Powered App itself.

For detailed instructions on creating App Builder objects, form views, and table views, see [Creating a Standard Application](./creating-a-standard-application.md). The same object can back a Standard App or a Workflow Powered App. The difference is in how you create the app from the elements of the App Builder framework. You can create the object, form views, and table views from the Objects entry in Applications Menu (![Applications Menu](../../images/icon-applications-menu.png)) &rarr; Applications &rarr; App Builder.

## **Object:** Create the object named _Role Change Request_.

## **Form:** Create the _Employee Request_ form.

   | Field Type | Field Label | Options | Required | Other Properties |
   | ---------- | ----------- | ------- | -------- | ---------------- |
   | Text | Name | Not applicable (NA) |  &#10004; | NA |
   | Text | Current Manager's Name | NA |  &#10004; | NA |
   | Text | Current Role | NA |  &#10004; | NA |
   | Select from List | Requested Role | Support Engineer <br /> Backend Developer <br /> Frontend Developer <br /> Technical Writer <br /> IT Engineer |  &#10004; | NA |

## **Form:** Create the _Current Manager's Assessment_ form.

   | Field Type | Field Label | Options | Required | Other Properties |
   | ---------- | ----------- | ------- | -------- | ---------------- |
   | Single Selection | Current Manager: Is the Transfer Approved? | Yes <br /> No |  &#10004; | NA |
   | Single Selection | Does the employee exhibit skills and experiences requisite with the requested role? | Yes <br /> No |  &#10004; | NA |
   | Text | Detail the employee's skills and characteristics that will make the transfer successful | NA |  &#10008; | Multiple Lines |
   | Text | Detail the employee's skills and characteristics that might inhibit a successful transfer | NA |  &#10008; | Multiple Lines |
   | Date | When can the transfer occur? | NA | &#10008; | NA |

## **Form:** Create the _Potential Manager's Assessment_ form.

   | Field Type | Field Label | Options | Required | Other Properties |
   | ---------- | ----------- | ------- | -------- | ---------------- |
   | Single Selection | Receiving Manager: Is the Transfer Approved? | Yes <br /> No |  &#10004; | NA |
   | Single Selection | Is there an available role for the employee? | Yes <br /> No |  &#10004; | NA |
   | Single Selection | Do you have concerns about the suitability of the employee? | Yes <br /> No |  &#10008; | NA |
   | Single Selection | Are training resources available? | Yes <br /> Unnecessary: The employee is already proficient in the subject matter. |  &#10008; | NA |
   | Text | Comments | NA |  &#10008; | Multiple Lines | NA |

## **Form:** Create the _HR Assessment_ form.

   | Field Type | Field Label | Options | Required | Other Properties |
   | ---------- | ----------- | ------- | -------- | ---------------- |
   | Single Selection | HR Representative: Is the Transfer Approved? | Yes <br /> No |  &#10004; | NA |
   | Single Selection | Does the transfer require a reassessment of the employee's benefits? | Yes <br /> No |  &#10004; | NA |

## **Table:** Create the _Role Change Request Master List_ table.

   | Column 1 | Column 2 | Column 3 | Column 4 | Column 5 | Column 6 |
   | ---------- | ----------- | ------- | -------- | ---------------- | ---- |
   | Name | Requested Role | Current Manager: Is the Transfer Approved? | Receiving Manager: Is the Transfer Approved? | HR Representative: Is the Transfer Approved? | When can the transfer occur? |

## Related Information

* [App Builder Overview](./app-builder-overview.md)
* [Creating a Standard Application](./creating-a-standard-application.md)
* [Workflow](../../process-automation/workflow/introduction-to-workflow.md)
* [Roles and Permissions](../../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md)
