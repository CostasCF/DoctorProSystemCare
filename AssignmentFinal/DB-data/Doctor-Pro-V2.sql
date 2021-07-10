CREATE TABLE "Patient" (
  "amka" char(11) UNIQUE PRIMARY KEY NOT NULL,
  "username" varchar(20) UNIQUE NOT NULL,
  "password" varchar(255) NOT NULL,
  "first_name" varchar(50) NOT NULL,
  "last_name" varchar(50) NOT NULL,
  "email" varchar(255) UNIQUE NOT NULL,
  "phone_num" varchar(20) UNIQUE NOT NULL
);

CREATE TABLE "Doctor" (
  "amka" char(11) UNIQUE PRIMARY KEY NOT NULL,
  "username" varchar(20) UNIQUE NOT NULL,
  "password" varchar(255) NOT NULL,
  "first_name" varchar(50) NOT NULL,
  "last_name" varchar(50) NOT NULL,
  "speciality" varchar(50) NOT NULL,
  "email" varchar(255) UNIQUE NOT NULL,
  "phone_num" varchar(20) UNIQUE NOT NULL,
  "admin_id" char(5) NOT NULL
);

CREATE TABLE "Admin" (
  "admin_id" char(6) UNIQUE PRIMARY KEY,
  "username" varchar(20) UNIQUE NOT NULL,
  "password" varchar(255) NOT NULL,
  "email" varchar(255) UNIQUE NOT NULL
);

CREATE TABLE "Appointment" (
  "appointment_id" char(6) UNIQUE PRIMARY KEY,
  "doctor_amka" char(11) NOT NULL,
  "patient_amka" char(11) NOT NULL,
  "date" date NOT NULL,
  "start_time" time NOT NULL,
  "end_time" time NOT NULL
);

ALTER TABLE "Appointment" ADD FOREIGN KEY ("patient_amka") REFERENCES "Patient" ("amka");

ALTER TABLE "Appointment" ADD FOREIGN KEY ("doctor_amka") REFERENCES "Doctor" ("amka");

ALTER TABLE "Doctor" ADD FOREIGN KEY ("admin_id") REFERENCES "Admin" ("admin_id");
