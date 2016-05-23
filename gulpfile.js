/**
 * Created by davidbuttar on 22/05/2016.
 */
'use strict';

var gulp = require('gulp');
var sass = require('gulp-sass');
var cleanCSS = require('gulp-clean-css');

gulp.task('sass', function () {
    return gulp.src('./site/sass/**/*.scss')
        .pipe(sass().on('error', sass.logError))
        .pipe(cleanCSS({compatibility: 'ie8'}))
        .pipe(gulp.dest('./site/css'));
});

gulp.task('sass:watch', function () {
    gulp.watch('./site/sass/**/*.scss', ['sass']);
});

gulp.task('default', ['sass']);
