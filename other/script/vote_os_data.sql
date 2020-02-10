-- --------------------------------------------------------
-- 主机:                           123.57.237.176
-- 服务器版本:                        5.7.27-0ubuntu0.16.04.1 - (Ubuntu)
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 vote_os 的数据库结构
CREATE DATABASE IF NOT EXISTS `vote_os` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `vote_os`;

-- 导出  表 vote_os.comment 结构
CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL DEFAULT '0',
  `topic_id` int(10) unsigned NOT NULL DEFAULT '0',
  `content` varchar(2000) NOT NULL,
  `vote` tinyint(3) NOT NULL DEFAULT '0',
  `insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- 正在导出表  vote_os.comment 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` (`id`, `user_id`, `topic_id`, `content`, `vote`, `insert_time`) VALUES
	(1, 2, 13, '可以的', 1, '2020-02-09 15:55:14'),
	(2, 3, 13, 'no', -1, '2020-02-09 15:56:57'),
	(3, 4, 13, 'yes', 1, '2020-02-09 15:57:55'),
	(4, 2, 14, 'y', 1, '2020-02-09 17:00:03'),
	(5, 3, 14, 'n', -1, '2020-02-09 17:00:20'),
	(6, 2, 13, 'H5版常见问题参考', -1, '2020-02-10 17:25:58'),
	(7, 2, 13, '项目 \'Vote-ui\' 编译成功.', -1, '2020-02-10 17:33:56'),
	(8, 2, 13, '项目 \'Vote-ui\' 编译成功.', 1, '2020-02-10 17:35:25'),
	(9, 2, 13, 'ttp://192.168.1.8:8080/', -1, '2020-02-10 17:35:42'),
	(10, 2, 16, '赞同', -1, '2020-02-10 19:03:10'),
	(11, 2, 16, '5版常见问题参考: https://ask.dcloud.net.cn/article/35232', -1, '2020-02-10 19:04:42'),
	(12, 2, 16, ' Local:   http://localhost:8080/ ', -1, '2020-02-10 19:05:07'),
	(13, 2, 15, ' https://ask.dcloud.net.cn/article/35232\n', 1, '2020-02-10 19:10:46'),
	(14, 2, 15, '项目 \'Vote-ui\' 编译成功.', -1, '2020-02-10 19:10:53'),
	(15, 2, 15, 'http://localhost:8080/ \n19:10:09.409   - Network: http://192.168.1.8:8080/', 1, '2020-02-10 19:11:22'),
	(16, 2, 15, 'tps://ask.dcloud.net.cn/article/35232', 1, '2020-02-10 19:11:49'),
	(17, 2, 15, 'oud.net.cn/article/35232\n19:10:09.403   App running at:\n19:10:09.406   - Local:   http://localhost:8', 1, '2020-02-10 19:12:09'),
	(18, 2, 15, 'http://localhost:8080/#/', -1, '2020-02-10 19:14:37'),
	(19, 2, 14, '发士大夫', -1, '2020-02-10 19:16:18'),
	(20, 2, 14, '阿发是否', 1, '2020-02-10 19:16:30'),
	(21, 2, 14, '项目 \'Vote-ui\' 编译成功.\n19:14:13.303 H5版常见问题参考: https://ask.dcloud.net.cn/article/35232', 1, '2020-02-10 19:18:05'),
	(22, 1, 18, '-ui\' 编译成功.\n19:14:13.303 H5版常见问题参考: https://ask.dcloud.net.cn/article/35232\n19:15:58.540   App runnin', 1, '2020-02-10 19:19:02');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;

-- 导出  表 vote_os.comment_vote 结构
CREATE TABLE IF NOT EXISTS `comment_vote` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `comment_id` int(10) unsigned NOT NULL,
  `vote` tinyint(4) NOT NULL,
  `insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- 正在导出表  vote_os.comment_vote 的数据：~9 rows (大约)
/*!40000 ALTER TABLE `comment_vote` DISABLE KEYS */;
INSERT INTO `comment_vote` (`id`, `user_id`, `comment_id`, `vote`, `insert_time`) VALUES
	(1, 3, 1, 1, '2020-02-09 15:58:38'),
	(2, 2, 1, 1, '2020-02-09 15:59:01'),
	(3, 4, 2, 1, '2020-02-09 15:59:35'),
	(4, 4, 3, 1, '2020-02-09 15:59:56'),
	(5, 2, 3, -1, '2020-02-09 16:00:08'),
	(6, 5, 3, 1, '2020-02-09 16:00:24'),
	(7, 2, 4, 1, '2020-02-09 17:01:18'),
	(8, 4, 5, -1, '2020-02-09 17:01:32'),
	(9, 4, 4, 1, '2020-02-09 17:02:23');
/*!40000 ALTER TABLE `comment_vote` ENABLE KEYS */;

-- 导出  表 vote_os.search_history 结构
CREATE TABLE IF NOT EXISTS `search_history` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `content` varchar(500) CHARACTER SET utf8 NOT NULL,
  `count` int(10) unsigned NOT NULL,
  `insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- 正在导出表  vote_os.search_history 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `search_history` DISABLE KEYS */;
INSERT INTO `search_history` (`id`, `user_id`, `content`, `count`, `insert_time`) VALUES
	(1, 1, 'a', 9, '2020-02-09 22:27:57'),
	(2, 1, 'dfssdf', 6, '2020-02-09 22:28:13');
/*!40000 ALTER TABLE `search_history` ENABLE KEYS */;

-- 导出  表 vote_os.topic 结构
CREATE TABLE IF NOT EXISTS `topic` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `title` varchar(500) NOT NULL,
  `notes` varchar(2000) DEFAULT NULL,
  `insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- 正在导出表  vote_os.topic 的数据：~9 rows (大约)
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` (`id`, `user_id`, `title`, `notes`, `insert_time`) VALUES
	(13, 2, '专家称新冠肺炎传播途径包括气溶胶传播，意味着什么？应如何防控？', NULL, '2020-02-09 15:35:50'),
	(14, 2, '程序员必须掌握哪些算法？', NULL, '2020-02-09 15:35:58'),
	(15, 2, '有没有那么一刻想抛弃你的宠物？', '一开始在外面喂，一段时间后天气越来越凉，看见它太瘦小冷的发抖，心软，带回来百般疼爱。', '2020-02-09 15:36:25'),
	(16, 2, '这些麻烦我都不怕，它没有智慧，只知道眼下的痛苦，抗拒也是正常的，我理解。', '当我稍微离开它眼前的时候，健胃消食片本来被他挖出来吐在了地上，它会偷偷捡去吃，因为酸酸甜甜的其实味道很好；也会听到哗啦哗啦的喝水声（板蓝根）；（板蓝根）拌的酸奶也会吃掉很多。', '2020-02-09 15:36:45'),
	(17, 2, '可是只要是我让他做的，它就宁死不屈！', '可能有些铲屎官觉得没什么，把狗狗当祖宗养，当孩子疼，由它们任性。可我从小也是个说一不二的性格，我也犟。就此时此刻我真的很讨厌它！对！讨厌！！！既然管不了，我真想彻底不管。就每顿把狗粮加上，爱吃不吃！药放嘴里，爱吐就吐！抱去医院打针，要挣扎便挣扎，懒得抱紧，扎上', '2020-02-09 22:07:00'),
	(18, 1, '大不了一针安乐扎死它算球！！！', '扔出去怕它传染别的狗！你霍霍了我一个人就行了！', '2020-02-09 22:19:52'),
	(19, 1, '项目 \'vote-ui\' 编译成功.', NULL, '2020-02-09 22:21:44'),
	(20, 1, '编译成功.', NULL, '2020-02-09 22:26:27'),
	(21, 1, '0 no need 1 after add new topic 2 switch', NULL, '2020-02-09 22:27:10');
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;

-- 导出  表 vote_os.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(200) NOT NULL,
  `insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- 正在导出表  vote_os.user 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `uid`, `insert_time`) VALUES
	(1, '1160130875fda0812c99c5e3f1a03516471a6370c4f97129b221938eb4763e63', '2020-01-12 10:52:24'),
	(2, 'e9db0ee382178b350fc636773e9a6949f0c13d9fbc4844c4cb72e9a434792091', '2020-02-08 21:26:00'),
	(3, 'd4dd23a6e252fc26445422a6b5480fab917d667fd023b35c444c653148cd3520', '2020-02-09 15:55:39'),
	(4, 'bbc9c6b604c84a679efcf8b0f2f4e42296fc4563a5094e9f67dd3f57e70b3090', '2020-02-09 15:55:47'),
	(5, '0deb16ccec5040a162aecd8f778781b4bf9f200d86b94aeaa9be1c608c6275d3', '2020-02-09 15:55:55'),
	(6, 'eb707e4080e7cc7a878a1c1eeb79fdbc85f4cb791315acf2b0a38a38bdbc10d5', '2020-02-09 15:56:04');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- 导出  表 vote_os.user_interest_topic 结构
CREATE TABLE IF NOT EXISTS `user_interest_topic` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `topic_id` int(11) unsigned NOT NULL,
  `insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- 正在导出表  vote_os.user_interest_topic 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `user_interest_topic` DISABLE KEYS */;
INSERT INTO `user_interest_topic` (`id`, `user_id`, `topic_id`, `insert_time`) VALUES
	(1, 1, 13, '2020-02-09 16:00:47'),
	(2, 1, 14, '2020-02-09 16:00:53'),
	(3, 4, 15, '2020-02-09 16:01:01'),
	(4, 2, 16, '2020-02-09 16:01:09'),
	(5, 3, 13, '2020-02-09 16:01:16'),
	(6, 3, 14, '2020-02-09 16:01:26');
/*!40000 ALTER TABLE `user_interest_topic` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
