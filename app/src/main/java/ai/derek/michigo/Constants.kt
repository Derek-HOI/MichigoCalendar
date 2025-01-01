package ai.derek.michigo

const val BASE_URL = "https://www.shbaseball.co.kr"

const val MsgNotFound = "<Not Found.>"

// html class
const val DivEmptyMath = "div.empty-match"
const val DivMatchDate = "div.match-date"
const val DivLeagueInfo = "div.league-info"
const val DivFinishMatch = "div.finish-match"
const val SpanResult = "span.result"
const val SpanResultSep = "finish"

const val DivLeftTime = "div.l-team"
const val DivRightTime = "div.r-team"
const val ImgTeamLogo = "img.team-logo"
const val SpanTeamName = "span.team-name"

/**
 * score 의 경우
 * hasClass("blue"), hasClass("red") 로 구분
 */
const val SpanScore = "span.score"

/**
 * a tag 확인, href 검사 후
 * @see HrefNull 인 경우 null
 * highlight 포함 시 하이라이트 영상
 * 그 외엔 상세
 */
const val DivButtonAreaAnchor = "div.btn-area a"

const val AttrSrc = "src"
const val AttrHref = "href"
const val HrefNull = "javascript:;"
