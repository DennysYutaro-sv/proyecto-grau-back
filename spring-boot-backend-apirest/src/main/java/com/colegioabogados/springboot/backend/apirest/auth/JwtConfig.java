package com.colegioabogados.springboot.backend.apirest.auth;

public class JwtConfig {
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	
	//Estas llaves generadas con openssl nos permitiran saber si nuestro token a sido manipulado y mandara error
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEpAIBAAKCAQEAy3th3erSFhaTfgeNr8U40puuoraQxBktigVQeeLhsXAYXMSH\r\n"
			+ "lLrrG9w0g+irnScwOjeWDGLpBQFlZJcM00+bNdsGrAeJMlF/C9fa9footG3Wa/DQ\r\n"
			+ "7E8nubp+EigWJ6+8ksOQSlG3vSzRRBysvZFsoeMTZ6AVpGaj6zNH+arCw/IKIdM6\r\n"
			+ "P/L2SXegBGrQOCoM9ojTp31G8UipEri4EfSV25AU+xL99Ih931aGTBJqoNZCpRAB\r\n"
			+ "g7fnsGaWdLLuLvWBH5WMLxwj+AMr7HDCrD9WSZUFci64VXN8bFmzHfmLEUJInGwO\r\n"
			+ "oyWPqSktvIT+on7D5Glo8rY8oJqo9ntf0XvmaQIDAQABAoIBAQC5T/uYJDQCQ7s9\r\n"
			+ "StnBmyadv4Sy0PtUa+2qRh69cl/CveiiC6XCUNyvecMl4q7wJ3Fng5oWW7oFgfj6\r\n"
			+ "PApFI7mhuYABCbfr6IaAnave2l1ZvLJLiXxrycbPAcWw+Wr757MgIhR1aqigE4Hd\r\n"
			+ "uxOibR9265uPgx5So9ti50gEW3yW/2Z89PlrpzEfBXpQQhpRvsmv+oHO/YRgO6ZW\r\n"
			+ "8xjQV7WWih5+miLjs0VX2f8aS27F493jYJNr19K6lSy75jmQ9hZbEAoyfKGtMPqy\r\n"
			+ "BkvZgplIvkKizRc47VhkXPekDUxCJILWiF7tit2ANH5em0vXZOjHsAp+4+7k9GyB\r\n"
			+ "uVZ0dKOBAoGBAOS4EE/Z5r4qmpfNijOkypa5e5skuAU1xUFJ2phL0l3IyUrPlv0J\r\n"
			+ "BXo3CgD8cw+VZL21aG3SB92v977sgrcljUZ7ehz6+iKf39CY94tRagIOo2ulSopA\r\n"
			+ "YxuIYEfxfyri6u4BDi14I1yEXSbPJyDZowE6WpwRf9BnIby2R1w7dbrZAoGBAOPA\r\n"
			+ "tJnahsidG9Qfk6X3IVteJFGksCGGq6GXedBf/jl4diPZbfi4QV0kZGPn1bQgGgii\r\n"
			+ "cE6vAGcv0OZgZLm5Ho+a2Fm1UcyHLL8KgGsWZthEXWC3H/ic/F45UabFmilZuRiQ\r\n"
			+ "7iPZGVKVShnUR9kZDNPzkQ7KvESHUL58C+r+I64RAoGASYvuot681hW5FGthr22k\r\n"
			+ "P4dxedf63KIHxhDLnAF1qfaw07+hG8jU3BHqw5pqHW66sE4vQHEzWlQFZH2FIwOO\r\n"
			+ "REXcZIlH+e5S+C7YGxKY9gRjBHBoYcCGngnie//qFo5VYScxuelR5aQimCIwwN17\r\n"
			+ "DyAGlv0BjrA7JwWuOLheCTECgYEArels/z77RR2TLnueR1Fay2Yr3ZwgUrl/Pblp\r\n"
			+ "b1Cd1EzECcPyUcaBQxqS/ib97LnGp2lC0RKZADJnFkPtJlTRnV7kbjwi5kciSA9A\r\n"
			+ "2IUWq3Yox72OUE2v+FNUokCp44F78eJZtk4FJCaTmv2aj5CtVvvafK0a7klVwt7+\r\n"
			+ "Sscdf8ECgYA9n9hSJ1ND0tnVuyN721Gg7Wx7AqSbGkKofH0sg+sa2DEqpRIBNEsz\r\n"
			+ "8Qp+6EKvM2CYn5mkw9krrZfXGMIeZQjR5EJtn07zZOqaWyUZudiAiNPLBxbpU3Qd\r\n"
			+ "qKax55jMcDR7eVjoDws94lPuqqaKn9aEuTqHpucSJ0XslvRSEW53Og==\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAy3th3erSFhaTfgeNr8U4\r\n"
			+ "0puuoraQxBktigVQeeLhsXAYXMSHlLrrG9w0g+irnScwOjeWDGLpBQFlZJcM00+b\r\n"
			+ "NdsGrAeJMlF/C9fa9footG3Wa/DQ7E8nubp+EigWJ6+8ksOQSlG3vSzRRBysvZFs\r\n"
			+ "oeMTZ6AVpGaj6zNH+arCw/IKIdM6P/L2SXegBGrQOCoM9ojTp31G8UipEri4EfSV\r\n"
			+ "25AU+xL99Ih931aGTBJqoNZCpRABg7fnsGaWdLLuLvWBH5WMLxwj+AMr7HDCrD9W\r\n"
			+ "SZUFci64VXN8bFmzHfmLEUJInGwOoyWPqSktvIT+on7D5Glo8rY8oJqo9ntf0Xvm\r\n"
			+ "aQIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";
}

