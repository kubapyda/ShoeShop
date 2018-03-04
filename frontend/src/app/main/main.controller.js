export class MainController {
  constructor ($timeout, toastr) {
    'ngInject';

    this.awesomeThings = [];
    this.classAnimation = '';
    this.creationDate = 1520129287297;
    this.toastr = toastr;

    this.activate($timeout);
  }

  activate($timeout) {
    $timeout(() => {
      this.classAnimation = 'rubberBand';
    }, 4000);
  }

  showToastr() {
    this.toastr.info('Fork <a href="https://github.com/Swiip/generator-gulp-angular" target="_blank"><b>generator-gulp-angular</b></a>');
    this.classAnimation = '';
  }
}
