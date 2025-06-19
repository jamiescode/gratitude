package com.jamiescode.showcase.gratitude.data.database

import androidx.room.InvalidationTracker
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class JournalEntryTest {
    @Test
    fun `database name is correct`() {
        val databaseName = JournalEntryDatabase.Companion.NAME
        assertEquals("journal-entry", databaseName)
    }

    @Test
    fun `database version is correct`() {
        val id = "1234567890"
        val journalEntry =
            JournalEntry(
                id = id,
                created = 100L,
                updated = 200L,
                entry = "entry",
                tags = "tag",
            )
        assertEquals(id, journalEntry.id)
        assertEquals(100L, journalEntry.created)
        assertEquals(200L, journalEntry.updated)
        assertEquals("entry", journalEntry.entry)
        assertEquals("tag", journalEntry.tags)
    }

    @Test
    fun `database creation`() {
        val journalEntryDatabase =
            object : JournalEntryDatabase() {
                override fun journalEntryDao(): JournalEntryDao = mockk(relaxed = true)

                override fun clearAllTables() {
                    // Noop
                }

                override fun createInvalidationTracker(): InvalidationTracker =
                    InvalidationTracker(
                        database = this,
                        shadowTablesMap = emptyMap(),
                        viewTables = emptyMap(),
                        tableNames = emptyArray(),
                    )
            }
        assertNotNull(journalEntryDatabase)
    }
}
