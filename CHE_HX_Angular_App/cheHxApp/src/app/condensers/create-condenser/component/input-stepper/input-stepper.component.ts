import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'stepper-input',
  templateUrl: './input-stepper.component.html',
  styleUrls: ['./input-stepper.component.scss'],
})
export class InputStepperComponent implements OnInit {
  @Input() initialValue;
  @Input() label;
  @Input() step: number = 0;
  @Input() min: number = 0;
  @Input() max: number = 0;
  @Input() symbol: string;
  @Input() checked: boolean;
  @Input() ariaLabelLess: string;
  @Input() ariaLabelMore: string;

  @Output() noOfSelectedField = new EventEmitter();

  renderedValue: number;
  value: number = 0;

  constructor() {}

  ngOnInit(): void {
    this.value = this.initialValue;
    this.renderedValue = this.value;
  }
  ngOnChanges(): void {
    if(this.checked === true){
      this.renderedValue = 1;
      this.value = 1;
      this.min = 1;
    } else {
      this.renderedValue = 0;
    }
  }
  toggleMore = () => {
    if (this.step + this.value <= this.max) {
      this.value = this.value + this.step;
      this.renderedValue = this.value;
    }
  };

  toggleLess = () => {
    if (this.value - this.step >= this.min) {
      console.log(this.value)
      this.value = this.value - this.step;
      this.renderedValue = this.value;
    }
  };
  emitInputVal(event) {
    this.noOfSelectedField.emit([event,this.label]);
  }
}
