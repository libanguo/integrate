<?xml version= "1.0" encoding= "utf-8"?>
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
<xsl:output method= "xml" encoding= "utf-8"/>
	<xsl:template match="classes">
	<classes>
		<xsl:for-each select="class">
			<class>
				<id>
					<xsl:value-of select="Cno"/>
					<xsl:value-of select="课程编号"/>
					<xsl:value-of select="编号"/>
				</id>
				<name>
					<xsl:value-of select="Cnm"/>
					<xsl:value-of select="课程名称"/>
					<xsl:value-of select="名称"/>
				</name>
				<score>
					<xsl:value-of select="Cpt"/>
					<xsl:value-of select="学分"/>
				</score>
				<teacher>
					<xsl:value-of select="Tec"/>
					<xsl:value-of select="授课老师"/>
					<xsl:value-of select="老师"/>
				</teacher>
				<location>
					<xsl:value-of select="Pla"/>
					<xsl:value-of select="授课地点"/>
					<xsl:value-of select="地点"/>
				</location>
			</class>
	</xsl:for-each>
	</classes>
	</xsl:template>
</xsl:stylesheet>