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
				<名称>
					<xsl:value-of select="name"/>
				</名称>
				<性别>
					<xsl:value-of select="sex"/>
				</性别>
				<专业>
					<xsl:value-of select="major"/>
				</专业>
			</student>
	</xsl:for-each>
	</students>
	</xsl:template>
</xsl:stylesheet>