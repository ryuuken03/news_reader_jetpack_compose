package mohammad.toriq.newsreader.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mohammad.toriq.newsreader.data.repository.ArticleRepositoryImpl
import mohammad.toriq.newsreader.data.source.remote.dto.ArticleAPIService
import mohammad.toriq.newsreader.domain.repository.ArticleRepository
import javax.inject.Singleton

/***
 * Created By Mohammad Toriq on 14/11/2023
 */
@Module
@InstallIn(SingletonComponent::class)
class ArticlesModule {
    @Provides
    @Singleton
    fun provideArticleRepositoryImpl(
        articleApiService: ArticleAPIService,
    ): ArticleRepository = ArticleRepositoryImpl(articleApiService)

}