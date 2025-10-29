INSERT INTO users 
( first_name, last_name, email, password, phone_no, role, approval_status, created_at)
VALUES 
-- Applicants
( 'Jahnavi', 'Reddy', 'jahnavi@applicant.com', 'pass123', '9876543210', 'APPLICANT', 'APPROVED', NOW()),
( 'Rahul', 'Kumar', 'rahul@applicant.com', 'pass123', '9876501234', 'APPLICANT', 'APPROVED', NOW()),

-- Recruiters
( 'Sneha', 'Sharma', 'sneha@recruiter.com', 'pass123', '9876512345', 'RECRUITER', 'WAITING', NOW()),
( 'Amit', 'Verma', 'amit@recruiter.com', 'pass123', '9876523456', 'RECRUITER', 'APPROVED', NOW()),
( 'Rohit', 'Singh', 'rohit@recruiter.com', 'pass123', '9876534567', 'RECRUITER', 'APPROVED', NOW());

INSERT INTO Recruiter_Profile 
( company_name, company_address, company_description, created_at, user_id)
VALUES
( 'Tech Solutions', 'Hyderabad', 'Leading IT company', NOW(), 4), 
( 'Innovatech', 'Bangalore', 'Software solutions provider', NOW(), 5),
( 'Data Analytics Corp', 'Pune', 'Analytics & ML company', NOW(), 3);




INSERT INTO Applicant_Profile ( experience_years, education, skills, about_you, project_urls, user_id)
VALUES 
( 2, 'B.Tech in ECE', 'Java, Python, SQL', 'Passionate about software development and data analytics', 'https://github.com/jahnavi', 1),
( 3, 'B.Tech in IT', 'Python, Selenium, Tableau', 'Interested in data-driven solutions and automation', 'https://github.com/rahul', 2);

INSERT INTO Job_Posting 
( job_title, job_description, job_location, job_type, job_salaryrange, job_requirements, yearsof_experience, onsite, recruiter_id, is_active, posted_at)
VALUES
( 'Java Developer', 'Develop Java applications', 'Hyderabad', 'FULL_TIME', '6-10 LPA', 'Java, Spring Boot', 2, TRUE, 1 , TRUE, NOW()),
( 'Frontend Developer', 'Work on Angular apps', 'Bangalore', 'FULL_TIME', '5-8 LPA', 'Angular, HTML, CSS, JS', 1, TRUE, 2, TRUE, NOW()),
( 'Backend Developer', 'API development', 'Chennai', 'FULL_TIME', '6-12 LPA', 'Node.js, Express', 3, TRUE, 3, TRUE, NOW()),
( 'Fullstack Developer', 'Build end-to-end apps', 'Mumbai', 'FULL_TIME', '7-14 LPA', 'React, Spring Boot, MySQL', 4, FALSE, 1, TRUE, NOW()),
( 'Data Analyst', 'Analyze business data', 'Pune', 'FULL_TIME', '4-7 LPA', 'SQL, Excel, Tableau', 1, TRUE, 2, TRUE, NOW()),
( 'ML Engineer', 'Build ML models', 'Hyderabad', 'FULL_TIME', '8-15 LPA', 'Python, Scikit-learn, TensorFlow', 3, TRUE, 3, TRUE, NOW()),
( 'QA Engineer', 'Test applications', 'Bangalore', 'FULL_TIME', '4-6 LPA', 'Selenium, TestNG', 2, TRUE, 1, TRUE, NOW()),
( 'DevOps Engineer', 'Maintain CI/CD pipelines', 'Chennai', 'FULL_TIME', '7-12 LPA', 'Docker, Jenkins, Kubernetes', 3, TRUE, 2, TRUE, NOW()),
( 'UI/UX Designer', 'Design web interfaces', 'Mumbai', 'FULL_TIME', '5-8 LPA', 'Figma, Adobe XD', 2, FALSE, 3, TRUE, NOW()),
( 'System Analyst', 'Analyze system requirements', 'Pune', 'FULL_TIME', '6-9 LPA', 'UML, SDLC', 2, TRUE, 1, TRUE, NOW()),
( 'Python Developer', 'Develop Python applications', 'Hyderabad', 'FULL_TIME', '5-10 LPA', 'Python, Django', 2, TRUE, 1, TRUE, NOW()),
( 'React Developer', 'Build React applications', 'Bangalore', 'FULL_TIME', '6-11 LPA', 'React, Redux', 2, TRUE, 2, TRUE, NOW()),
( 'Angular Developer', 'Develop Angular front-end', 'Chennai', 'FULL_TIME', '5-9 LPA', 'Angular, RxJS', 1, TRUE, 3, TRUE, NOW()),
( 'Node.js Developer', 'Server-side development', 'Mumbai', 'FULL_TIME', '6-12 LPA', 'Node.js, Express, MongoDB', 3, TRUE, 1, TRUE, NOW()),
( 'Data Engineer', 'Data pipeline development', 'Pune', 'FULL_TIME', '7-13 LPA', 'Python, Spark, SQL', 3, TRUE, 2, TRUE, NOW()),
( 'Business Analyst', 'Analyze business needs', 'Hyderabad', 'FULL_TIME', '5-8 LPA', 'Excel, PowerBI', 2, TRUE, 3, TRUE, NOW()),
( 'Cloud Engineer', 'Manage cloud infrastructure', 'Bangalore', 'FULL_TIME', '8-15 LPA', 'AWS, Azure, GCP', 4, TRUE, 1, TRUE, NOW()),
( 'Security Analyst', 'Security monitoring', 'Chennai', 'FULL_TIME', '6-10 LPA', 'Network Security, SIEM', 2, TRUE, 2, TRUE, NOW()),
( 'Product Manager', 'Manage product lifecycle', 'Mumbai', 'FULL_TIME', '10-18 LPA', 'Agile, Scrum', 5, FALSE, 3, TRUE, NOW()),
( 'SEO Specialist', 'Optimize website', 'Pune', 'FULL_TIME', '4-7 LPA', 'SEO, Google Analytics', 1, TRUE, 1, TRUE, NOW()),
( 'Java Intern', 'Assist Java team', 'Hyderabad', 'INTERNSHIP', '10-15K', 'Java Basics', 0, TRUE, 2, TRUE, NOW()),
( 'Frontend Intern', 'Assist frontend team', 'Bangalore', 'INTERNSHIP', '10-15K', 'HTML, CSS, JS', 0, TRUE, 3, TRUE, NOW()),
( 'Backend Intern', 'Assist backend team', 'Chennai', 'INTERNSHIP', '12-18K', 'Node.js, SQL', 0, TRUE, 1, TRUE, NOW()),
( 'Fullstack Intern', 'Assist fullstack team', 'Mumbai', 'INTERNSHIP', '15-20K', 'React, Spring', 0, TRUE, 2, TRUE, NOW()),
( 'Database Administrator', 'Maintain databases', 'Pune', 'FULL_TIME', '6-10 LPA', 'SQL, MySQL, Oracle', 3, TRUE, 3, TRUE, NOW()),
( 'Technical Writer', 'Write tech docs', 'Hyderabad', 'FULL_TIME', '4-7 LPA', 'Documentation, API docs', 2, TRUE, 1, TRUE, NOW()),
( 'Mobile Developer', 'Android/iOS apps', 'Bangalore', 'FULL_TIME', '6-12 LPA', 'Flutter, Kotlin, Swift', 3, TRUE, 2, TRUE, NOW()),
( 'UI Developer', 'Front-end UI development', 'Chennai', 'FULL_TIME', '5-9 LPA', 'HTML, CSS, JS, Angular', 2, TRUE, 3, TRUE, NOW()),
( 'Data Scientist', 'Analyze data & ML', 'Mumbai', 'FULL_TIME', '8-15 LPA', 'Python, ML, Pandas', 4, TRUE, 1, TRUE, NOW()),
( 'DevOps Intern', 'Assist in CI/CD', 'Pune', 'INTERNSHIP', '10-15K', 'Jenkins, Docker basics', 0, TRUE, 2, TRUE, NOW());




INSERT INTO digital_resume
( user_id, careerobjective, education, work_experience, courses, extra_curricular, skills, certifications, projects)
VALUES
( 1,
 'To secure a challenging position in a reputable organization to expand my learning, knowledge, and skills.',
 'B.Tech in Electronics and Communication Engineering, IIIT Sri City (2021–2025)',
 'Intern at TCS — worked on backend microservices using Spring Boot and SQL.',
 'Data Structures & Algorithms, Machine Learning, Database Management Systems',
 'Sports Secretary, Student Life Council Member',
 'C++, Python, SQL, Spring Boot, Selenium, Angular',
 'Java Foundations Certified (Oracle), Data Analytics Certificate (Coursera)',
 'Job Portal Application using Spring Boot and Angular, InstaBot Automation using Selenium'
),

( 2,
 'To leverage my frontend development skills to build seamless and user-friendly interfaces.',
 'B.Tech in Computer Science, NIT Trichy (2020–2024)',
 'Frontend Intern at Zoho — built dashboards using ReactJS and REST APIs.',
 'Web Development with React, UI/UX Design Principles',
 'Technical Head - Web Club',
 'HTML, CSS, JavaScript, React, TypeScript, TailwindCSS',
 'Frontend Developer Certification (freeCodeCamp)',
 'E-commerce Website using ReactJS and Firebase'
);

INSERT INTO Applications ( job_id, cover_letter, status, digital_resume_id)
VALUES
( 1, 'I have 2 years of backend experience and strong knowledge in Spring Boot.', 'PENDING', 1),
( 2, 'Passionate about front-end development and eager to work with Angular.', 'REVIEWED', 2);

