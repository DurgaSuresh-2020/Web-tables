Feature: Table items validations
Description: Purpose of this feature is to validate the table values of Sample data table from Contexture page

    Background: Launch the Contexture page
        Given I launch Contexure Page
        And accept cookies if needed

    Scenario: How many different items are there (binder/pencil etc.) ?
        Then fetch different items available in the table

    Scenario Outline: Is there an item with less than <Units> units?
        Then fetch items with units less than <Units>
        Examples:
            | Units |
            | 5     |

    Scenario Outline: Is there a <Item> with less than <Units> units?
        Then fetch "<Item>" with units less than <Units>
        Examples:
            | Item   | Units |
            | pencil | 5     |

    Scenario: What is the most expensive item on the list?
         Then get the most expensive item in the table