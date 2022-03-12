CREATE TABLE `spr_url` (
  `id` varchar(255) NOT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `uri` varchar(255) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;