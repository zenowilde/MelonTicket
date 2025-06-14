-- melonticket.concert definition

CREATE TABLE `concert` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `venue` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `rating` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=270 DEFAULT CHARSET=utf8mb4;