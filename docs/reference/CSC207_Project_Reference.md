# CSC207 Fall 2026 (UofT): Course Project Reference

> **Sources:** (1) the "Project Expectations" and "Project Timeline" text from the course Quercus page, and (2) the *Project Rubric 2026* PDF (dated Nov 14, 2026).
> Requirements and rubric wording follow the originals. Sections marked *(derived)* are summaries added for quick lookup, not official course text.
> **Compiled:** 2026-11-16

---

## 0. At a glance

- **Build:** a team Java desktop app with a **Java Swing GUI**, following **Clean Architecture** and **SOLID**, using at least one **external API** and some form of **data persistence**.
- **Scope:** **one user story per team member**. Each member **leads at least one use case** (writes the majority of its code).
- **Process:** use **Pull Requests** throughout. Instructors **check git logs** for each person's contributions.
- **Final deliverable:** a **15-minute** team presentation with a live demo at the **Dec 7/8 labs**, graded **out of 100**.

---

## 1. Hard requirements (must all be met)

| # | Requirement | Notes |
|---|---|---|
| R1 | **Pull Requests** used throughout development | Must be demonstrable. |
| R2 | **Java program with a Java Swing GUI** | JavaFX only with a compelling reason and instructor approval. **Web apps will not be approved.** |
| R3 | **Scope = one user story per team member** | |
| R4 | **Attempt to adhere to Clean Architecture + SOLID** | At the final presentation, the team talks about the design, demos the functionality, and must be ready to **justify design decisions**. (SOLID was covered in week 6; Clean Architecture in weeks 7–8.) |
| R5 | **Use an external API** | A Java library that wraps the API calls is fine. (The week 7 lab covered APIs.) |
| R6 | **Data persistence** | Can be via an API, writing to files, or a database. |

**Shortcut for R5 + R6:** the course's user-login example class `DBUserDataAccessObject` stores and retrieves a "user" plus an associated JSON object through an API. Using that API satisfies **both** the API and persistence requirements. It suits projects that need to share data across multiple users and devices.
- Reference (updated Nov 7, with the correct API URL for this term): https://github.com/CSC207-2026F-UofT/ca-login/blob/db-user/src/main/java/data_access/DBUserDataAccessObject.java

**If unsure whether the project meets any requirement → ask the instructors early.**

---

## 2. Individual contribution expectations

Each person is assessed on their own contribution.

- **Lead at least one use case.** The team can divide the work however it likes, but each member must write **the majority of the code for one use case**.
- **Attend** all labs and team meetings, or tell the team if you can't make one.
- **Participate actively every week.**
- **Contribute an appropriate amount to the GitHub repo:**
  - Instructors **check the team's git logs**.
  - Do your fair share, and **don't write code that is another member's responsibility**.
  - **No visible code contributions from you = no project credit for you.**
  - **If one person wrote all the code, that person can't get credit either** (it's a group project).

---

## 3. Timeline and milestones

Each lab is working time. The team is expected to make progress outside class too, e.g. assign tasks at the lab, then post async updates or hold check-ins between labs.

| Milestone | Lab | Due |
|---|---|---|
| M1: Project Planning & Initial Development | Nov 9/10 | **Fri Nov 13, 2026** |
| M2: Individual Progress & Working Prototype | Nov 16/17 | **Fri Nov 20, 2026** |
| M3: Near-Complete Prototype | Nov 23/24 | **Fri Nov 27, 2026** |
| M4: Final Polish & Presentation Prep | Nov 30/Dec 1 | **Fri Dec 4, 2026** |
| **Final Presentations** | **Dec 7/8** | — |

### M1: Project Planning & Initial Development (due Fri Nov 13)
- [ ] GitHub repo created and shared with all team members.
- [ ] README contains the information outlined on the course page "Project README and Minimum Viable Product (MVP)": https://q.utoronto.ca/courses/444184/pages/project-readme-and-minimum-viable-product-mvp *(that page's contents are not captured here)*
- [ ] Project Blueprint finalized in the README, with a **clearly defined minimal set of use cases** the team commits to as the MVP.
  - [ ] Each MVP use case is **assigned to a team member and documented in the README**.
  - [ ] Every member leads at least one use case.
- [ ] **Core entities** designed and implemented.
- [ ] Initial progress on assigned use cases, **including UIs and use case interactors**.

### M2: Individual Progress & Working Prototype (due Fri Nov 20)
- [ ] **Every member** has:
  - [ ] submitted **at least one substantial pull request** for their assigned work,
  - [ ] **reviewed at least one PR** created by another member,
  - [ ] **runnable code** for their assigned use case that they can demo and discuss with the TA.
- [ ] Team has runnable code that **makes calls to every external API** the project uses.
- [ ] Team has a **minimal end-to-end prototype** to demo to the TA (**at least one complete use case**).

### M3: Near-Complete Prototype (due Fri Nov 27)
- [ ] **At least one unit test for each interactor.**
- [ ] Project is near-complete and ready to demo to the TA for final feedback.
  - [ ] MVP functionality implemented and **all use cases integrated** into the application.

### M4: Final Polish & Presentation Prep (due Fri Dec 4)
- [ ] Tests achieve **100% line coverage for the Entity and Use Case layers**.
- [ ] Outstanding bugs fixed, and the app is presentation-ready.
- [ ] Team presentation polished and practiced.

### Final Presentations: Dec 7/8 labs

---

## 4. Grading rubric (out of 100)

Each criterion is scored on five levels: **≤1/5 Inadequate · 2/5 Acceptable · 3/5 Good · 4/5 Excellent · 5/5 Exceptional**. The "Weight" column is the criterion's "Out of" value.

### 4.1 Weight summary

| Area *(derived grouping)* | Criterion | Weight |
|---|---|---|
| Presentation (45) | Presentation Materials (slides, visuals) | 15 |
| | Presentation Delivery (clarity, rehearsal, timing) | 15 |
| | Demo | 10 |
| | Question Answering | 5 |
| Spec & scope (15) | Specification (MVP / User Stories / Use Cases) | 10 |
| | Project Scope | 5 |
| Technical requirements (10) | API Usage | 5 |
| | Data Persistence | 5 |
| Design & quality (30) | Clean Architecture | 10 |
| | Overall Design (SOLID and Design Patterns) | 10 |
| | Code Quality | 10 |
| | **Total** | **100** |

### 4.2 Full level descriptors

#### Presentation Materials (slides, visuals): 15
- **≤1 Inadequate:** Slides are significantly incomplete; many required elements are missing.
- **2 Acceptable:** Most required elements are present, but some are thin or missing. Slides lack polish or contain significant errors (e.g., typos, unreadable text, cluttered slides).
- **3 Good:** All required elements are present and slides are readable, with only minor errors. Could still be improved in specific ways, such as better use of images or diagrams, or proofreading.
- **4 Excellent:** All required elements are present and polished, with no noticeable errors. Visuals (e.g., diagrams, screenshots) are used effectively throughout.
- **5 Exceptional:** Slides look professional and go beyond expectations. Visuals make complex ideas easy to follow; significant effort is evident.

#### Presentation Delivery (clarity, rehearsal, timing): 15
- **≤1 Inadequate:** Presentation was clearly not rehearsed and is hard to follow.
- **2 Acceptable:** Limited evidence of rehearsal. Frequent pauses, reading directly from slides, or unclear explanations.
- **3 Good:** The team had clearly prepared and the presentation is mostly clear and organized. Some specific weaknesses, such as uneven pacing, rough transitions, or some reading from slides.
- **4 Excellent:** Clear, well-paced, and smooth, with speakers explaining rather than reading. **Required for 4/5 or higher: within the 15-minute limit and every group member speaks.**
- **5 Exceptional:** Confident, engaging, and clearly rehearsed as a team. Transitions between speakers are seamless.

#### Demo: 10
- **≤1 Inadequate:** No demo, or the demo fails and nothing working is shown.
- **2 Acceptable:** Demo shows only part of the team's use cases, or has significant problems (e.g., crashes, long setup delays). Little explanation of what is being shown.
- **3 Good:** Demo shows most use cases working, with minor hiccups. What is being shown is mostly explained.
- **4 Excellent:** Demo runs smoothly end-to-end through the team's use cases, with data and setup prepared in advance. Each step is explained clearly as it is shown.
- **5 Exceptional:** Demo is seamless and well chosen, clearly showing **each member's user story** in action. It clearly connects back to the design and use cases discussed in the slides.

#### Question Answering: 5
- **≤1 Inadequate:** The team is unable to answer questions about the project.
- **2 Acceptable:** The team is able to answer some of the questions asked.
- **3 Good:** The team is able to answer most questions asked. Few to no obvious errors.
- **4 Excellent:** The team is able to answer all questions asked. No obvious errors in the responses.
- **5 Exceptional:** The team was able to effectively handle all questions asked. Answers are factually accurate, clear, and concise. Answers demonstrated a deep knowledge of the project and course content.

#### Specification (MVP / User Stories / Use Cases): 10
- **≤1 Inadequate:** The specification is incomplete and difficult to understand. The specification includes implementation details.
- **2 Acceptable:** The specification is somewhat clear, but has room to be improved. It somewhat focuses on what the program will do, but there is still some emphasis on how it does it.
- **3 Good:** The specification is mostly clear, but could be polished. It mostly focuses on what the program will do, but there is still some emphasis on how it does it.
- **4 Excellent:** The specification is clear and easy to understand. It clearly focuses on **what** the program will do **without mention of how** it does it.
- **5 Exceptional:** The specification is extremely clear and easy to understand. It clearly focuses on what the program will do without mention of how it does it. **A non-programmer unfamiliar with the project can easily understand the purpose of the software.**

#### Project Scope: 5
- **≤1 Inadequate:** The project scope is insufficient; there are fewer user stories specified than group members.
- **2 Acceptable:** The project scope is acceptable, but shows little ambition; there is at least one user story per group member, but they are all minimal or significantly overlap with other user stories.
- **3 Good:** The project scope is somewhat ambitious; each member has at least one significant user story, but there is some overlap between them.
- **4 Excellent:** The project scope is appropriately ambitious; each member has at least one significant user story and they don't significantly overlap.
- **5 Exceptional:** The project scope is notably impressive, going clearly above and beyond course expectations; each member has at least one significant user story and the user stories are distinct.

#### API Usage: 5
- **≤1 Inadequate:** No API is used in the project.
- **2 Acceptable:** Minimal use of an API in the project. The API usage is not clearly motivated by the program requirements.
- **3 Good:** Team made use of one or more APIs in their project. The slides mention how the API was used in the project, but details are lacking.
- **4 Excellent:** Team made use of one or more APIs in their project. The slides mention **specifics** about how the API was used in the project.
- **5 Exceptional:** Team made **significant** use of one or more APIs in their project. It is clear how the API was used and the API was clearly appropriate for the project.

#### Data Persistence: 5
- **≤1 Inadequate:** No data persistence included in the project.
- **2 Acceptable:** Data persistence is attempted but not implemented. No clear evidence is provided in the presentation.
- **3 Good:** Data persistence is at least partially implemented. Minimal evidence is provided in the presentation.
- **4 Excellent:** Data persistence is implemented. Some evidence provided in the presentation.
- **5 Exceptional:** Data persistence is clearly evident in the project. **Both saving and loading data is clearly demonstrated.**

#### Clean Architecture: 10
- **≤1 Inadequate:** Project has few attempts at following the Clean Architecture. Dependency Rule clearly violated in the project.
- **2 Acceptable:** Project appears to somewhat adhere to the Clean Architecture, but there are significant violations of the Dependency Rule. Insufficient discussion of the overall architecture during the presentation. Little to no use of effective diagrams to convey architectural information.
- **3 Good:** Project appears to largely adhere to Clean Architecture. The slides contain **at least one appropriate diagram** to convey the overall architecture and provide evidence that the Clean Architecture is being followed.
- **4 Excellent:** Project appears to almost completely adhere to CA. The slides contain appropriate diagrams to convey the overall architecture and provide strong evidence that CA is being followed. Any violations are clearly discussed and adequately justified.
- **5 Exceptional:** Project appears to adhere to CA. **No violations of the Dependency Rule are evident.** The group went above and beyond to convince the audience of this in the presentation through effective use of architectural diagrams. Any minor violations are clearly discussed and adequately justified.

#### Overall Design (SOLID and Design Patterns): 10
- **≤1 Inadequate:** Little to no attempt to discuss the design has been incorporated into the presentation.
- **2 Acceptable:** There is some discussion of at least ONE of the SOLID principles, but it is rather limited. There is an attempt to discuss a design pattern or talk about the extensibility of the program, but it is rather limited.
- **3 Good:** A rich discussion of at least **ONE** of the SOLID principles is included. **AND EITHER** a design pattern appropriate to the project, beyond those already implemented in the provided starter code, has been implemented and is clearly described, **OR** an example of how the program has been designed to be extensible has been clearly described.
- **4 Excellent:** A rich discussion of at least **TWO** of the SOLID principles is included. A design pattern appropriate to the project, **beyond those already implemented in the provided starter code**, has been implemented and is clearly described. **AND** an example of how the program has been designed to be extensible has been clearly described.
- **5 Exceptional:** The group goes above and beyond in conveying the information outlined at the Excellent level, including significant and appropriate use of **at least one design pattern not presented in class**.

#### Code Quality: 10
- **≤1 Inadequate:** Little to no evidence of any team efforts to ensure code quality.
- **2 Acceptable:** Some effort was made to ensure code quality, but the details are significantly unclear or the process seems to be inadequate.
- **3 Good:** The team clearly put in a reasonable effort to ensure code quality in the main branch. Some details were unclear, but no concerns about the process the team was using.
- **4 Excellent:** Significant effort was clearly put into ensuring high-quality code was being merged into main. **A tool like Checkstyle MUST be used to earn this level or above**; all style errors need not be addressed, but evidence of use must be provided.
- **5 Exceptional:** Significant effort was clearly put into ensuring high-quality code was being merged into main. The team goes above and beyond in how they describe their process for ensuring code quality, e.g., **with a representative pull request example**.

---

## 5. Key thresholds and gotchas *(derived from the official text above)*

**Score gates (miss these and the score is capped):**
- **Delivery 4/5+** needs the talk to stay **under 15 minutes** and **every member must speak**.
- **Code Quality 4/5+** needs **Checkstyle (or similar)**, with evidence of use shown. 5/5 adds a representative PR example of the review process.
- **Overall Design:**
  - Design patterns must go **beyond those in the starter code**.
  - 4/5 needs **two** SOLID principles discussed richly, **plus** a pattern, **plus** an extensibility example.
  - 5/5 needs a pattern **not presented in class**.

**What the slides and demo must show:**
- **Clean Architecture:** use **architecture diagrams** in the slides. Explicitly **discuss and justify any Dependency Rule violations**.
- **Data Persistence 5/5:** show **both saving and loading** in the demo.
- **API Usage 4/5+:** the slides need **specifics** on how the API is used. 5/5 means the API use is significant and clearly fits the project.
- **Demo 5/5:** show **each member's user story**, and tie it back to the design and use cases in the slides.

**Spec, scope, and testing:**
- **Specification:** write about **what** the app does, not **how**. A non-programmer should understand it (5/5).
- **Scope:** user stories should be **significant and distinct**. Overlap or minimal stories lose marks.
- **Testing:** have at least **1 unit test per interactor** by Nov 27, and **100% line coverage on the Entity and Use Case layers** by Dec 4.
- **Individual credit:** you need **your own visible commits/PRs** and at least one PR you reviewed. Don't write teammates' use cases.

---

## 6. Our team

Team decisions, ownership and conventions live in `CLAUDE.md` at the repo root (source of truth).
