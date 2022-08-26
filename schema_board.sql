DROP TABLE IF EXISTS post;

CREATE TABLE IF NOT EXISTS post(
  id varchar(255) NOT NULL,
  author varchar(255) NOT NULL,
  title varchar(255) NOT NULL,
  body varchar(255) NOT NULL,
  deleted bool NOT NULL,
  created_date timestamp NULL,
  updated_date timestamp NULL,
  PRIMARY KEY (id)
);
