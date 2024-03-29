<?xml version= "1.0" encoding= "utf-8"?>
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
<xsl:output method= "xml" encoding= "utf-8"/>
	<xsl:template match="classes">
	<classes>
		<xsl:for-each select="class">
			<class>
				<Cno>
					<xsl:value-of select="id"/>
				</Cno>
				<Cnm>
					<xsl:value-of select="name"/>
				</Cnm>
				<Cpt>
					<xsl:value-of select="score"/>
				</Cpt>
				<Tec>
					<xsl:value-of select="teacher"/>
				</Tec>
				<Pla>
					<xsl:value-of select="location"/>
				</Pla>
			</class>
	</xsl:for-each>
	</classes>
	</xsl:template>
</xsl:stylesheet>