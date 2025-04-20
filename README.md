# Primer Evaluation Tool

A simple web application to analyze DNA primers.

## Technologies

- Java 10 or 11
- Thymeleaf
- Gradle

## Features

- Analyze forward (and optional reverse) primers
- Calculates:
  - GC percentage
  - Melting temperature (Tm)
  - Max homopolymer stretch
  - Max 3' intra- and intermolecular identity
- History: Stores and displays the last 5 primer analyses with a one-click "Re-run" option.

## Getting Started

### Prerequisites
- Java 10+ or 11 installed
- Gradle installed

### Installation

- Clone the repository:

```bash
git clone https://github.com/eaooms/Primer-evaluation-tool.git
cd Primer-evaluation-tool/Primer-Tool/
```

- Build the project:

```bash
./gradlew build
```
- After the build is complete

```bash
./gradlew bootRun
```

Then open your browser at:  
[http://localhost:8080](http://localhost:8080)

## Usage

1. Enter a name and sequence for the forward primer.
2. (Optional) Enter a name and sequence for the reverse primer.
3. Click Analyseer to view results.
4. Review metrics: GC%, Tm, homopolymer stretch, 3′-inter/intramolecular identity.
5. Use the Re-run button in the history panel to repeat an analysis.

## Project Structure

- `webcontrol/` – Routing and request handling  
- `service/` – Primer analysis logic  
- `model/` – Data models  
- `templates/` – Thymeleaf HTML templates  
- `static/css/` – Styling

