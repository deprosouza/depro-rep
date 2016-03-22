
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<xsl:template match="/">
		<xsl:apply-templates select="ativacao/login" />
		<br/>
		<br/>
		http://mugetsuanimes.bounceme.net:8080/mugetsu-web/ativacao/?CA=<xsl:value-of select="ativacao/codigoAtivacao" />
	</xsl:template>
	
	<xsl:template match="ativacao/login">
		<xsl:apply-templates select="usuario" />
	</xsl:template>
	
	<xsl:template match="usuario">
		Bem vindo <xsl:value-of select="primeiroNome" /> <xsl:value-of select="ultimoNome" /><br/>
		Seu cadastro foi realizado com sucesso, para ativar sua conta clique no link abaixo.
	</xsl:template>

</xsl:stylesheet> 