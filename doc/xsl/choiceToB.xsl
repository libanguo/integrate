<?xml version= "1.0" encoding= "utf-8"?>
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
<xsl:output method= "xml" encoding= "utf-8"/>
	<xsl:template match="choices">
	<choices>
		<xsl:for-each select="choice">
			<choice>
				<学号>
					<xsl:value-of select="sid"/>
				</学号>
				<课程编号>
					<xsl:value-of select="cid"/>
				</课程编号>
				<得分>
					<xsl:value-of select="score"/>
				</得分>
			</choice>
		</xsl:for-each>
	</choices>
	</xsl:template>
</xsl:stylesheet>