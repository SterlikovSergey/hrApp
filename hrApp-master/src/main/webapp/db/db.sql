SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema hr_support
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hr_support
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hr_support` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `hr_support` ;

-- -----------------------------------------------------
-- Table `hr_support`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr_support`.`user` (
  `id_user` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `activation_code` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `role` INT(11) NULL DEFAULT NULL,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_user`))
ENGINE = InnoDB
AUTO_INCREMENT = 24
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr_support`.`candidate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr_support`.`candidate` (
  `id_candidate` BIGINT(20) NOT NULL,
  `birthday` DATETIME(6) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `gender` VARCHAR(255) NULL DEFAULT NULL,
  `mob_phone` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `photo` VARCHAR(255) NULL DEFAULT NULL,
  `surname` VARCHAR(255) NULL DEFAULT NULL,
  `id_user` BIGINT(20) NOT NULL,
  `hr_email` VARCHAR(255) NULL DEFAULT NULL,
  `session_code` VARCHAR(255) NULL DEFAULT NULL,
  `facebook_link` VARCHAR(255) NULL DEFAULT NULL,
  `linked_link` VARCHAR(255) NULL DEFAULT NULL,
  `twitter_link` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_candidate`),
  UNIQUE INDEX `UK_h58r42s4c6hess9d17xw9nbk4` (`id_user` ASC) VISIBLE,
  CONSTRAINT `FKijeixigb97l0g0gtom8i1ev8e`
    FOREIGN KEY (`id_user`)
    REFERENCES `hr_support`.`user` (`id_user`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr_support`.`anketa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr_support`.`anketa` (
  `id` BIGINT(20) NOT NULL,
  `question1` VARCHAR(255) NULL DEFAULT NULL,
  `question2` VARCHAR(255) NULL DEFAULT NULL,
  `question3` VARCHAR(255) NULL DEFAULT NULL,
  `question4` VARCHAR(255) NULL DEFAULT NULL,
  `question5` VARCHAR(255) NULL DEFAULT NULL,
  `question6` VARCHAR(255) NULL DEFAULT NULL,
  `question7` VARCHAR(255) NULL DEFAULT NULL,
  `question8` VARCHAR(255) NULL DEFAULT NULL,
  `resume` VARCHAR(255) NULL DEFAULT NULL,
  `review` VARCHAR(255) NULL DEFAULT NULL,
  `id_candidate` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_m0geg34usuf538u0njrmxf16w` (`id_candidate` ASC) VISIBLE,
  CONSTRAINT `FK3s449ucmxi8nmpspvbqx17sy9`
    FOREIGN KEY (`id_candidate`)
    REFERENCES `hr_support`.`candidate` (`id_candidate`)
    ON DELETE SET NULL
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr_support`.`chat_message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr_support`.`chat_message` (
  `id` INT(11) NOT NULL,
  `message` VARCHAR(255) NULL DEFAULT NULL,
  `time` VARCHAR(255) NULL DEFAULT NULL,
  `user_from` BIGINT(20) NULL DEFAULT NULL,
  `user_to` BIGINT(20) NULL DEFAULT NULL,
  INDEX `FK1q9haqwwpfsdw8o7m4v720nxi` (`user_to` ASC) VISIBLE,
  INDEX `FKclv25nv60q62hb3ucugys6aa4` (`user_from` ASC) VISIBLE,
  CONSTRAINT `FK1q9haqwwpfsdw8o7m4v720nxi`
    FOREIGN KEY (`user_to`)
    REFERENCES `hr_support`.`user` (`id_user`),
  CONSTRAINT `FKclv25nv60q62hb3ucugys6aa4`
    FOREIGN KEY (`user_from`)
    REFERENCES `hr_support`.`user` (`id_user`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr_support`.`organization_structure`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr_support`.`organization_structure` (
  `id_post` BIGINT(20) NOT NULL,
  `department` VARCHAR(255) NULL DEFAULT NULL,
  `post` VARCHAR(255) NULL DEFAULT NULL,
  `salary` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`id_post`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr_support`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr_support`.`employee` (
  `id_employee` BIGINT(20) NOT NULL,
  `birthday` DATETIME(6) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `gender` VARCHAR(255) NULL DEFAULT NULL,
  `is_working` INT(11) NULL DEFAULT NULL,
  `mobphone` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `pasp_date` DATETIME(6) NULL DEFAULT NULL,
  `pasp_id` VARCHAR(14) NULL DEFAULT NULL,
  `pasp_ser` VARCHAR(9) NULL DEFAULT NULL,
  `pasp_num` VARCHAR(255) NULL DEFAULT NULL,
  `photo` VARCHAR(255) NULL DEFAULT NULL,
  `rate` DOUBLE NULL DEFAULT NULL,
  `surname` VARCHAR(255) NULL DEFAULT NULL,
  `id_post` BIGINT(20) NULL DEFAULT NULL,
  `id_user` BIGINT(20) NULL DEFAULT NULL,
  `facebook_link` VARCHAR(255) NULL DEFAULT NULL,
  `linked_link` VARCHAR(255) NULL DEFAULT NULL,
  `twitter_link` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_employee`),
  UNIQUE INDEX `UK_o9gex07wht6p49953tj4pv9va` (`id_user` ASC) VISIBLE,
  INDEX `FKr2uaaeysfdwf28l3pu3llfiao` (`id_post` ASC) VISIBLE,
  CONSTRAINT `FKdtvbvnxc0q7vsp1ev909vrtx6`
    FOREIGN KEY (`id_user`)
    REFERENCES `hr_support`.`user` (`id_user`),
  CONSTRAINT `FKr2uaaeysfdwf28l3pu3llfiao`
    FOREIGN KEY (`id_post`)
    REFERENCES `hr_support`.`organization_structure` (`id_post`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr_support`.`log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr_support`.`log` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `info` VARCHAR(255) NULL DEFAULT NULL,
  `user` BIGINT(20) NULL DEFAULT NULL,
  `time` DATETIME(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKb8ags6ghboidawvk1bsfoea0s` (`user` ASC) VISIBLE,
  CONSTRAINT `FKb8ags6ghboidawvk1bsfoea0s`
    FOREIGN KEY (`user`)
    REFERENCES `hr_support`.`user` (`id_user`))
ENGINE = InnoDB
AUTO_INCREMENT = 230
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr_support`.`payroll`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr_support`.`payroll` (
  `id` BIGINT(20) NOT NULL,
  `accrued` DOUBLE NULL DEFAULT NULL,
  `allowances` DOUBLE NULL DEFAULT NULL,
  `date` DATETIME(6) NULL DEFAULT NULL,
  `hold` DOUBLE NULL DEFAULT NULL,
  `total` DOUBLE NULL DEFAULT NULL,
  `id_employee` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKi2387aut138490moka9s6fitp` (`id_employee` ASC) VISIBLE,
  CONSTRAINT `FKi2387aut138490moka9s6fitp`
    FOREIGN KEY (`id_employee`)
    REFERENCES `hr_support`.`employee` (`id_employee`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr_support`.`timetable`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr_support`.`timetable` (
  `id` BIGINT(20) NOT NULL,
  `type` VARCHAR(255) NULL DEFAULT NULL,
  `id_candidate` BIGINT(20) NULL DEFAULT NULL,
  `id_employee` BIGINT(20) NOT NULL,
  `color` VARCHAR(255) NULL DEFAULT NULL,
  `end_date` DATETIME(6) NULL DEFAULT NULL,
  `end_time` DATETIME(6) NULL DEFAULT NULL,
  `start_date` DATETIME(6) NULL DEFAULT NULL,
  `start_time` DATETIME(6) NULL DEFAULT NULL,
  `url` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKdktstjyxpwqhlyhyocyr433ud` (`id_employee` ASC) VISIBLE,
  INDEX `FK8csrnihclt9tc5tcve3ff8td2` (`id_candidate` ASC) VISIBLE,
  CONSTRAINT `FK8csrnihclt9tc5tcve3ff8td2`
    FOREIGN KEY (`id_candidate`)
    REFERENCES `hr_support`.`candidate` (`id_candidate`)
    ON DELETE SET NULL,
  CONSTRAINT `FKdktstjyxpwqhlyhyocyr433ud`
    FOREIGN KEY (`id_employee`)
    REFERENCES `hr_support`.`employee` (`id_employee`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr_support`.`сertificate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr_support`.`сertificate` (
  `id` BIGINT(20) NOT NULL,
  `order_date` DATETIME(6) NULL DEFAULT NULL,
  `percent` INT(11) NULL DEFAULT NULL,
  `type` VARCHAR(255) NULL DEFAULT NULL,
  `id_employee` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKrb2qxe50q1i47xtxibgtbdasl` (`id_employee` ASC) VISIBLE,
  CONSTRAINT `FKrb2qxe50q1i47xtxibgtbdasl`
    FOREIGN KEY (`id_employee`)
    REFERENCES `hr_support`.`employee` (`id_employee`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
