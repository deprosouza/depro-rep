INSERT INTO Permissao (id, chave, descricao, habilitado) VALUES (1, 'RM_MENU_CONSULTA',	     'Menu de Consulta', true);
INSERT INTO Permissao (id, chave, descricao, habilitado, nivel) VALUES (2, 'RM_CONSULTA_PLAYER',	 'Consulta de Player', true, 1);

INSERT INTO Role (id, chave, descricao, nivel) VALUES (1, 'ROLE_ADMIN', 'Administrador das midias em geral',5);
INSERT INTO Role (id, chave, descricao, nivel) VALUES (2, 'ROLE_USER',  'Usuário comum do sistema', 1);
INSERT INTO Role (id, chave, descricao, nivel) VALUES (3, 'NO_ROLE',	'Visitante do sistema', 0);

INSERT INTO RolePermissao (role_fk, permissao_fk) VALUES (2,1);
INSERT INTO RolePermissao (role_fk, permissao_fk) VALUES (2,2);
INSERT INTO RolePermissao (role_fk, permissao_fk) VALUES (3,1);
INSERT INTO RolePermissao (role_fk, permissao_fk) VALUES (3,2);

INSERT INTO Login (id, username, email, senha, dataCadastro) VALUES (1, 'rsouza', 'rsouza.ds@gmail.com', 'wqimkp12', sysdate());

INSERT INTO LoginRole (login_fk, role_fk) VALUES (1,1);
INSERT INTO LoginRole (login_fk, role_fk) VALUES (1,2);