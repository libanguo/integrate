<?xml version= "1.0" encoding= "utf-8"?>
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
<xsl:output method= "xml" encoding= "utf-8"/>
	<xsl:template match="students">
	<students>
		<xsl:for-each select="student">
			<student>
				<学号>
					<xsl:value-of select="id"/>
				</学号>
				<姓名>
					<xsl:value-of select="name"/>
				</姓名>
				<性别>
					<xsl:value-of select="sex"/>
				</性别>
				<院系>
					<xsl:value-of select="major"/>
				</院系>
			</student>
	</xsl:for-each>
	</students>
	</xsl:template>
</xsl:stylesheet>