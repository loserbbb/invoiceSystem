USE invoicedb;

CREATE TABLE `tb_inputtax` (
  `uuid` VARCHAR(50) NOT NULL COMMENT '货物数据编号',
  `itemName` VARCHAR(50) NOT NULL COMMENT '货物名称',
  `standard` VARCHAR(50) DEFAULT NULL COMMENT '规格型号',
  `unit` VARCHAR(20) NOT NULL COMMENT '计量单位',
  `count` DECIMAL(10,2) NOT NULL COMMENT '数量',
  `price_includeTax` DECIMAL(10,2) NOT NULL COMMENT '单价（含税）',
  `price_unincludeTax` DECIMAL(10,2) NOT NULL COMMENT '单价（不含税）',
  `totalMoney_includeTax` DECIMAL(10,2) NOT NULL COMMENT '总金额（含税）',
  `totalMoney_unincludeTax` DECIMAL(10,2) NOT NULL COMMENT '总金额（不含税）',
  `leviable` DECIMAL(10,2) NOT NULL COMMENT '征收率',
  `amountTax` DECIMAL(10,2) NOT NULL COMMENT '税额',
  `fromPath` VARCHAR(50) NOT NULL COMMENT '购货路径',
  `timer` DATETIME NOT NULL COMMENT '购货时间',
  PRIMARY KEY (`uuid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8

CREATE TABLE `tb_outputtax` (
  `uuid` VARCHAR(50) NOT NULL COMMENT '货物数据编号',
  `itemName` VARCHAR(50) NOT NULL COMMENT '货物名称',
  `standard` VARCHAR(50) DEFAULT NULL COMMENT '规格型号',
  `unit` VARCHAR(20) NOT NULL COMMENT '计量单位',
  `count` DECIMAL(10,2) NOT NULL COMMENT '数量',
  `price_includeTax` DECIMAL(10,2) NOT NULL COMMENT '单价(含税)',
  `price_unincludeTax` DECIMAL(10,2) NOT NULL COMMENT '单价（不含税）',
  `totalMoney_includeTax` DECIMAL(10,2) NOT NULL COMMENT '总金额(含税)',
  `totalMoney_unincludeTax` DECIMAL(10,2) NOT NULL COMMENT '总金额（不含税）',
  `leviable` DECIMAL(10,2) NOT NULL COMMENT '征收率',
  `amountTax` DECIMAL(10,2) NOT NULL COMMENT '税额',
  `toPath` VARCHAR(50) NOT NULL COMMENT '销售路径',
  `timer` DATETIME NOT NULL COMMENT '销售时间',
  PRIMARY KEY (`uuid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8

CREATE TABLE `tb_userinfo` (
  `uid` VARCHAR(30) NOT NULL COMMENT '用户编号',
  `uName` VARCHAR(20) NOT NULL COMMENT '用户名',
  `uPassword` VARCHAR(20) NOT NULL COMMENT '密码',
  `uRemmberpwd` TINYINT(1) NOT NULL COMMENT '是否保存密码',
  PRIMARY KEY (`uid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8