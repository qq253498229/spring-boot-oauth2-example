import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatePersonalDetailComponent } from './update-personal-detail.component';

describe('UpdatePersonalDetailComponent', () => {
  let component: UpdatePersonalDetailComponent;
  let fixture: ComponentFixture<UpdatePersonalDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdatePersonalDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdatePersonalDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
