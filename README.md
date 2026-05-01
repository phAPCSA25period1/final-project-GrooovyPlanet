# Java Calendar Application

## Overview

This is a simple command-line calendar application written in Java. It allows users to create, view, search, update, and delete events. The program is interactive and menu-driven, making it easy to use directly from the terminal.

---

## Features

* Add events with a title and date
* Prevent duplicate events (same title + date)
* Remove events by title
* View all events
* Search events:

  * By exact date
  * By month and year
* Update existing events
* Built-in step-by-step tutorial mode

---

## Project Structure

### `Event` Class

Represents a single calendar event.

**Fields:**

* `title` – name of the event
* `date` – event date in `YYYY-MM-DD` format

**Methods:**

* `getTitle()` – returns event title
* `getDate()` – returns event date
* `toString()` – returns formatted event string

---

### `Calendar` Class

Manages a list of events using an `ArrayList`.

**Key Methods:**

* `addEvent(Event event)` – adds a new event (checks for duplicates)
* `removeByTitle(String title)` – removes events by title
* `displayEvents()` – prints all events
* `getEventsByDate(String date)` – filters events by exact date
* `getEventsByMonthYear(int month, int year)` – filters by month/year
* `updateEvent(String title, String date, Event updatedEvent)` – updates an existing event

---

### `Main` Class

Handles user interaction through a menu-driven interface.

**Menu Options:**

1. Add event
2. Remove event
3. View all events
4. Search events
5. Update event
6. Exit
7. Run tutorial

---

## How to Run

### 1. Compile the program

```bash
javac Main.java
```

### 2. Run the program

```bash
java Main
```

---

## Usage Notes

* Dates must be entered in **YYYY-MM-DD** format (e.g., `2026-05-01`)
* Event titles are case-insensitive when checking duplicates or removing
* Removing by title will delete all matching events
* Updating requires both the original title and date

---

## Example

```
--- Calendar Menu ---
1. Add event
2. Remove event by title
3. View all events
4. Search by date
5. Update event
6. Exit
7. Run tutorial
```

---

## Tutorial Mode

Select option `7` to run a guided walkthrough of all features. This is helpful for first-time users.

---

## Possible Improvements

* Validate date format using `LocalDate`
* Add time support for events
* Store events in a file (persistence)
* Sort events chronologically
* Add unique IDs instead of relying on title/date

---

## Author

Developed as a simple Java project to demonstrate object-oriented programming, collections, and user interaction via the console.

---
