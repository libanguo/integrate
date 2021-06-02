<?xml version= "1.0" encoding= "utf-8"?>
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
<xsl:output method= "xml" encoding= "utf-8"/>
	<xsl:template match="choices">
	<choices>
		<xsl:for-each select="choice">
			<choice>
				<Sno>
					<xsl:value-of select="sid"/>
				</Sno>
				<Cno>
					<xsl:value-of select="cid"/>
				</Cno>
				<Grd>
					<xsl:value-of select="score"/>
				</Grd>
			</choice>
		</xsl:for-each>
	</choices>
	</xsl:template>
</xsl:stylesheet>