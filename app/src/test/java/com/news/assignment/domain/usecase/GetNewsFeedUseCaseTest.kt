package com.news.assignment.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import androidx.paging.testing.asSnapshot
import com.news.assignment.domain.model.NewsArticle
import com.news.assignment.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.flow
import kotlin.test.fail

class GetNewsFeedUseCaseTest {

    private lateinit var repository: NewsFeedRepository
    private lateinit var useCase: GetNewsFeedUseCase

    @Before
    fun setUp() {
        repository = mock()
        useCase = GetNewsFeedUseCase(repository)
    }

    @Test
    fun `useCase returns correct articles`() = runTest {
        val dummyArticles = listOf(
            NewsArticle(
                title = "Title 1",
                articleUrl = "https://example.com/1",
                author = null,
                description = "This is a descriptive text for the article, providing a brief summary of its content. It should be long enough to give context but concise.",
                imageUrl = null,
                publishedDate = "2025-07-26",
                sourceName = "News Source 1"
            ),
            NewsArticle(
                title = "Title 2",
                articleUrl = "https://example.com/2",
                author = null,
                description = "This is a descriptive text for the article, providing a brief summary of its content. It should be long enough to give context but concise.",
                imageUrl = null,
                publishedDate = "2025-07-26",
                sourceName = "News Source 2"
            )
        )

        val fakePagingData = PagingData.from(dummyArticles)
        whenever(repository.fetchNewsFeed()).thenReturn(flowOf(fakePagingData))

        val result = useCase.invoke()
        val items: List<NewsArticle> = result.asSnapshot()

        assertThat(items).hasSize(2)
        assertThat(items[0].title).isEqualTo("Title 1")
    }

    @Test
    fun `useCase returns empty list when no articles`() = runTest {
        whenever(repository.fetchNewsFeed()).thenReturn(flowOf(PagingData.from(emptyList())))

        val result = useCase.invoke()
        val items = result.asSnapshot()

        assertThat(items).isEmpty()
    }


    @Test
    fun `useCase emits error when repository fails`() = runTest {
        whenever(repository.fetchNewsFeed()).thenAnswer {
            flow<PagingData<NewsArticle>> {
                throw RuntimeException("Network error")
            }
        }

        val resultFlow = useCase.invoke()
        try {
            resultFlow.asSnapshot()
            fail("Expected RuntimeException was not thrown")
        } catch (e: RuntimeException) {
            assertThat(e.message).isEqualTo("Network error")
        }
    }

}
