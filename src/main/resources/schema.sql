
CREATE TABLE InputData (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           field1 VARCHAR(255),
                           field2 VARCHAR(255),
                           field3 VARCHAR(255),
                           field4 VARCHAR(255),
                           field5 DECIMAL(10,2),
                           refkey1 VARCHAR(255),
                           refkey2 VARCHAR(255)
);

CREATE TABLE OutputData (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            outfield1 VARCHAR(255),
                            outfield2 VARCHAR(255),
                            outfield3 VARCHAR(255),
                            outfield4 VARCHAR(255),
                            outfield5 DECIMAL(10,2)
);

CREATE TABLE ReferenceData(
                           id BIGINT AUTO_INCREMENT PRIMARY KEY ,
                           refkey1 VARCHAR(255),
                           refdata1 VARCHAR(255),
                           refkey2 VARCHAR(255),
                           refdata2 VARCHAR(255),
                           refdata3 VARCHAR(255),
                           refdata4 DECIMAL(10,2)
)
