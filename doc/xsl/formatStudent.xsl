<?xml version= "1.0" encoding= "utf-8"?>
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
<xsl:output method= "xml" encoding= "utf-8"/>
	<xsl:template match="students">
	<students>
		<xsl:for-each select="student">
			<student>
				<id>
					<xsl:value-of select="Sno"/>
					<xsl:value-of select="学号"/>
					<xsl:value-of select="学号"/>
				</id>
				<name>
					<xsl:value-of select="Snm"/>
					<xsl:value-of select="姓名"/>
					<xsl:value-of select="名称"/>
				</name>
				<sex>
					<xsl:value-of select="Sex"/>
					<xsl:value-of select="性别"/>
					<xsl:value-of select="性别"/>
				</sex>
				<major>
					<xsl:value-of select="Sde"/>
					<xsl:value-of select="院系"/>
					<xsl:value-of select="专业"/>
				</major>
			</student>
		</xsl:for-each>
	</students>
	</xsl:template>
</xsl:stylesheet>