import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoachReservationStepperComponent } from './coach-reservation-stepper.component';

describe('CoachReservationStepperComponent', () => {
  let component: CoachReservationStepperComponent;
  let fixture: ComponentFixture<CoachReservationStepperComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoachReservationStepperComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CoachReservationStepperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
