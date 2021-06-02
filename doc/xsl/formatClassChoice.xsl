<?xml version= "1.0" encoding= "utf-8"?>
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
<xsl:output method= "xml" encoding= "utf-8"/>
	<xsl:template match="choices">
	<choices>
		<xsl:for-each select="choice">
			<choice>
				<sid>
					<xsl:value-of select="Sno"/>
					<xsl:value-of select="学号"/>
					<xsl:value-of select="学生编号"/>
				</sid>
				<cid>
					<xsl:value-of select="Cno"/>
					<xsl:value-of select="课程编号"/>
				</cid>
				<score>
					<xsl:value-of select="Grd"/>
					<xsl:value-of select="得分"/>
					<xsl:value-of select="成绩"/>
				</score>
			</choice>
	</xsl:for-each>
	</choices>
	</xsl:template>
</xsl:stylesheet>