package mohammad.toriq.newsreader.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mohammad.toriq.newsreader.domain.repository.ArticleRepository
import mohammad.toriq.newsreader.domain.usecase.GetArticleTopHeadlines
import javax.inject.Singleton

/***
 * Created By Mohammad Toriq on 14/11/2023
 */
@Module
@InstallIn(SingletonComponent::class)
class ArticlesUseCaseModule {

    @Provides
    @Singleton
    fun provideGetArticlesUseCase(repository: ArticleRepository): GetArticleTopHeadlines =
        GetArticleTopHeadlines(repository)
}